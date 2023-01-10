package com.info.leavemodule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.leavemodule.model.Employee;
import com.info.leavemodule.model.Holiday;
import com.info.leavemodule.model.Leave;
import com.info.leavemodule.repository.EmployeeRepository;
import com.info.leavemodule.repository.LeaveRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private LeaveService leaveService;
    private LeaveRepository leaveRepository;

    public Employee addNewEmployee(Employee employee) {
        setLeaveRights(employee);

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        List<Leave> leaveIds = leaveRepository.getLeavesByEmployee_Id(id);

        if (optionalEmployee.isPresent()) {
            optionalEmployee.get().setLeaves(leaveIds);
            return optionalEmployee.get();
        } else {
            return null;
        }
    }

    private List<String > getPublicHolidays() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> dates = new ArrayList<>();
        try {
            Holiday[] holiday = objectMapper.readValue(new URL("https://date.nager.at/api/v2/publicholidays/2023/TR"), Holiday[].class);
            for (int i = 0; i < holiday.length; i++) {
                //System.out.println(holiday[i].getDate());
                dates.add(holiday[i].getDate());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dates;
    }

    public static boolean isWeekend(final LocalDate date) {
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));

        if(day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
            return true;
        } else {
            return false;
        }
    }

    public Employee confirmEmployeeLeave(Long employee_id, Long leave_id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee_id);
        int difference = setEmployeeLeave(employee_id, leave_id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (difference > employee.getRemainingLeaveRights()) {
                System.out.println("Kalan izin hakkınızdan daha fazlasını kullanamazsınız. Lütfen yöneticinizle iletişime geçin!");
                leaveRepository.findById(leave_id).get().setLeaveStatus("Reddedildi");//izin onaylanmaz
                leaveRepository.save(leaveRepository.findById(leave_id).get());
                return null;
            } else {
                int remainingLeaveRights = employee.getRemainingLeaveRights() - difference;
                employee.setRemainingLeaveRights(employee.getRemainingLeaveRights() - difference);//kullanılacak olan izin kalan izin hakkından düşülür.
                leaveRepository.findById(leave_id).get().setLeaveStatus("Onaylandı");//izin onaylanır
                leaveRepository.save(leaveRepository.findById(leave_id).get());
                System.out.println("Kalan izin hakkınız: " + remainingLeaveRights);
                return employeeRepository.save(employee);
            }
        } else {
            return null;
        }
    }

    public Employee setLeaveRights(Employee employee) {

        int differenceDays =(int) DAYS.between(employee.getStartDate(), LocalDate.now());

        if (differenceDays < 365) {
            employee.setRemainingLeaveRights(0);
            employee.setAdvanceDay(5);
        } else if(differenceDays >= 365 && differenceDays < 1825) {
            employee.setRemainingLeaveRights(15);
            employee.setAdvanceDay(0);
        } else if (differenceDays >= 1825 && differenceDays < 3650) {
            employee.setRemainingLeaveRights(18);
            employee.setAdvanceDay(0);
        } else {
            employee.setRemainingLeaveRights(24);
            employee.setAdvanceDay(0);
        }

        return employee;
    }

    public int setEmployeeLeave(Long employee_id, Long leave_id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee_id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Optional<Leave> leaveOptional = leaveRepository.findById(leave_id);
            if (leaveOptional.isPresent()) {
                Leave leave = leaveOptional.get();
                int offDays = controlHolidaysAndWeekends(leave.getStartDate(), leave.getEndDate().plusDays(1));
                int differenceDays = leaveService.findNumberOfLeaveDays(leave.getStartDate(), leave.getEndDate());
                System.out.println("Kullanılacak gün hakkı sayısı: " + (differenceDays - offDays));

                return differenceDays - offDays;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public int controlHolidaysAndWeekends(LocalDate startDate, LocalDate endDate) {
        int numberOfHolidaysInLeave = 0;
        List<String> holidayList = getPublicHolidays();
        List<LocalDate> listOfDates = startDate.datesUntil(endDate).toList();

        for (LocalDate ld : listOfDates) {
            if (isWeekend(ld)) {
                numberOfHolidaysInLeave++;
                continue;
            }
            for (String ldh : holidayList) {
                if (ld.equals(ldh)) {
                    numberOfHolidaysInLeave++;
                }
            }
        }

        return numberOfHolidaysInLeave;
    }

}

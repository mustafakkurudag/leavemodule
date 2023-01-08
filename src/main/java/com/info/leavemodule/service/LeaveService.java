package com.info.leavemodule.service;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.model.Leave;
import com.info.leavemodule.repository.EmployeeRepository;
import com.info.leavemodule.repository.LeaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
public class LeaveService {

    private LeaveRepository leaveRepository;
    private EmployeeRepository employeeRepository;

    public Leave createNewLeave(Long id, Leave leave) {
        if (employeeRepository.findById(id).isPresent()) {
            Employee employee = employeeRepository.findById(id).get();
            leave.setLeaveStatus("Onay Bekleniyor");
            leave.setEmployee(employee);
            return leaveRepository.save(leave);
        } else {
            return null;
        }
    }

    public List<Leave> getLeaveIdsByEmployeeId(Long id) {
        List<Leave> leaveIds = leaveRepository.getLeavesByEmployee_Id(id);
        for (Leave l :
                leaveIds) {
            System.out.println("Id geldi" + l.getId());
        }

        return leaveIds;
    }



    public int findNumberOfLeaveDays(LocalDate startDate, LocalDate endDate) {
        int differenceDays =(int) DAYS.between(startDate, endDate);
        return differenceDays;
    }

}

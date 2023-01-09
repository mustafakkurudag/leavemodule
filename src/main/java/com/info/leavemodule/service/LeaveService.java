package com.info.leavemodule.service;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.model.Leave;
import com.info.leavemodule.repository.EmployeeRepository;
import com.info.leavemodule.repository.LeaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
public class LeaveService {

    private LeaveRepository leaveRepository;
    private EmployeeRepository employeeRepository;

    public Leave createNewLeave(Long employee_id, Leave leave) {
        if (employeeRepository.findById(employee_id).isPresent()) {
            Employee employee = employeeRepository.findById(employee_id).get();
            leave.setLeaveStatus("Onay Bekleniyor");
            leave.setEmployee(employee);
            return leaveRepository.save(leave);
        } else {
            return null;
        }
    }

    public List<Leave> getLeavesByEmployeeId(Long id) {
        List<Leave> leaves = leaveRepository.getLeavesByEmployee_Id(id);

        return leaves;
    }

    public int findNumberOfLeaveDays(LocalDate startDate, LocalDate endDate) {
        int differenceDays =(int) DAYS.between(startDate, endDate);
        return differenceDays;
    }

}

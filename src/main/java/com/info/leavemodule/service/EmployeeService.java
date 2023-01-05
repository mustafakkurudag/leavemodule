package com.info.leavemodule.service;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}

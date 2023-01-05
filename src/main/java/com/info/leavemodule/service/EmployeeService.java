package com.info.leavemodule.service;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public Employee addNewEmployee(Employee employee) {
        setLeaveRights(employee);

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            return null;
        }
    }

    public Employee setLeaveRights(Employee employee) {

        int differenceDays =(int) ChronoUnit.DAYS.between(employee.getStartDate().toInstant(),new Date().toInstant());

        if (differenceDays < 365) {
            employee.setRemainingLeaveRights(0);
        } else if(differenceDays >= 365 && differenceDays < 1825) {
            employee.setRemainingLeaveRights(15);
        } else if (differenceDays >= 1825 && differenceDays < 3650) {
            employee.setRemainingLeaveRights(18);
        } else {
            employee.setRemainingLeaveRights(24);
        }

        return employee;
    }
}

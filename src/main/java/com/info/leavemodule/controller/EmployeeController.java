package com.info.leavemodule.controller;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/new")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee newEmployee) {
        Employee emp = employeeService.addNewEmployee(newEmployee);

        return ResponseEntity.ok().body(emp);
    }



}

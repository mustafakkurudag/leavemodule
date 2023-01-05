package com.info.leavemodule.controller;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get/all")
    public ResponseEntity<List<Employee>> showEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> showOneEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(employee);
        }
    }


}

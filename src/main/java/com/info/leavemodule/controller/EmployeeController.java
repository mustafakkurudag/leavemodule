package com.info.leavemodule.controller;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.model.Leave;
import com.info.leavemodule.service.EmployeeService;
import com.info.leavemodule.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    private LeaveService leaveService;

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
            employee.setLeaves(leaveService.getLeaveIdsByEmployeeId(id));
            return ResponseEntity.ok().body(employee);
        }
    }

    @GetMapping("/get/{id}/leaves")
    public ResponseEntity<List<Leave>> showEmployeeLeaves(@PathVariable("id") Long id) {
        List<Leave> leaves = leaveService.getLeaveIdsByEmployeeId(id);
        return ResponseEntity.ok().body(leaves);
    }

    @PutMapping("/update/{id}/{leave_id}")
    public ResponseEntity<?> updateEmployeeLeave(@PathVariable("id") Long id, @PathVariable("leave_id") Long leave_id) {
        Employee employee = employeeService.confirmEmployeeLeave(id, leave_id);
        if (employee != null) {
            return ResponseEntity.ok().body(employee);
        } else {
            return ResponseEntity.ok().body("İzniniz onaylanmadı. Lütfen yöneticinizle iletişime geçin");
        }

    }

}

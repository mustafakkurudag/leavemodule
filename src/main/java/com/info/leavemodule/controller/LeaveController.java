package com.info.leavemodule.controller;

import com.info.leavemodule.model.Leave;
import com.info.leavemodule.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
@AllArgsConstructor
public class LeaveController {

    private LeaveService leaveService;

    @PostMapping("/new/{employee_id}")
    public ResponseEntity<String> createNewLeaveRequest(@PathVariable("employee_id") Long employee_id, @RequestBody Leave leave) {
        leaveService.createNewLeave(employee_id, leave);
        return ResponseEntity.ok().body("Talebiniz alındı. Onay durumunu sistemden takip edebilirsiniz.");
    }


}

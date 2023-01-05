package com.info.leavemodule.controller;

import com.info.leavemodule.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leave")
@AllArgsConstructor
public class LeaveController {

    private LeaveService leaveService;

    @PostMapping("/new")
    public ResponseEntity<String> createNewLeaveRequest() {
        return ResponseEntity.ok().body("Talebiniz alındı. Onay durumunu sistemden takip edebilirsiniz.");
    }
}

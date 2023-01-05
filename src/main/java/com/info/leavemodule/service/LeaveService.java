package com.info.leavemodule.service;

import com.info.leavemodule.repository.LeaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaveService {

    private LeaveRepository leaveRepository;
}

package com.info.leavemodule.service;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.model.Leave;
import com.info.leavemodule.repository.EmployeeRepository;
import com.info.leavemodule.repository.LeaveRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
class LeaveServiceTest {

    private LeaveService leaveService;

    private LeaveRepository leaveRepository;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        leaveRepository = Mockito.mock(LeaveRepository.class);
        employeeRepository = Mockito.mock(EmployeeRepository.class);

        leaveService = new LeaveService(leaveRepository, employeeRepository);
    }

    @Test
    public void createNewLeaveTest() {
        Leave leave = Leave.builder()
                .id(10L)
                .startDate(LocalDate.of(2022, 10, 5))
                .endDate(LocalDate.of(2022, 10, 15))
                .leaveStatus("Onay Bekleniyor")
                .employee(new Employee())
                .build();

        leaveService.createNewLeave(10L, leave);

        Assertions.assertThat(leave.getId()).isGreaterThan(0);
    }

    @Test
    public void getLeavesByEmployeeIdTest() {
        when(leaveRepository.getLeavesByEmployee_Id(10L)).thenReturn(List.of(new Leave(), new Leave()));
        Assertions.assertThat(leaveService.getLeavesByEmployeeId(10L)).hasSize(2);
        verify(leaveRepository, times(1)).getLeavesByEmployee_Id(10L);
        verifyNoMoreInteractions(leaveRepository);
    }

}
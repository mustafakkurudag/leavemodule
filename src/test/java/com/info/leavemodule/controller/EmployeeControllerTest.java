package com.info.leavemodule.controller;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.service.EmployeeService;
import com.info.leavemodule.service.LeaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://www.youtube.com/watch?v=ZMAzrndno2E&t=555s
 */
class EmployeeControllerTest {

    private EmployeeController employeeController;

    private EmployeeService employeeService;
    private LeaveService leaveService;

    @BeforeEach
    void setUp() {
        employeeService = Mockito.mock(EmployeeService.class);
        leaveService = Mockito.mock(LeaveService.class);

        employeeController = new EmployeeController(employeeService, leaveService);
    }

    @Test
    void addNewEmployee() {
        Employee employee = Employee.builder()
                .id(10L)
                .identificationNumber("12345678901")
                .firstName("Selim")
                .lastName("Yürekli")
                .designation("Civil Engineer")
                .startDate(LocalDate.of(2023,2,6))
                .remainingLeaveRights(0)
                .advanceDay(5)
                .leaves(leaveService.getLeavesByEmployeeId(10L))
                .build();

    }

    @Test
    void showEmployees() {
    }

    @Test
    void showOneEmployee() {
    }

    @Test
    void showEmployeeLeaves() {
    }

    @Test
    void updateEmployeeLeave() {
    }
}
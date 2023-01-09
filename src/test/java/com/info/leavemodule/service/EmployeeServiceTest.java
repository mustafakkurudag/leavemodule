package com.info.leavemodule.service;

import com.info.leavemodule.model.Employee;
import com.info.leavemodule.repository.EmployeeRepository;
import com.info.leavemodule.repository.LeaveRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {

    private EmployeeService employeeService;

    private EmployeeRepository employeeRepository;
    private LeaveRepository leaveRepository;
    private LeaveService leaveService;

    @BeforeEach
    public void setUp() throws Exception {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        leaveRepository = Mockito.mock(LeaveRepository.class);
        leaveService = Mockito.mock(LeaveService.class);

        employeeService = new EmployeeService(employeeRepository, leaveService, leaveRepository);
    }

    @Test
    public void addEmployeeTest() {
        Employee employee = Employee.builder()
                .id(10L)
                .identificationNumber("12345678901")
                .firstName("Selim")
                .lastName("Yürekli")
                .designation("Civil Engineer")
                .startDate(LocalDate.of(2023,2,6))
                .remainingLeaveRights(0)
                .advanceDay(5)
                .leaves(leaveRepository.getLeavesByEmployee_Id(10L))
                .build();

        employeeService.addNewEmployee(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    public void getAllEmployeesTest() {
        when(employeeRepository.findAll()).thenReturn(List.of(new Employee(), new Employee()));
        Assertions.assertThat(employeeService.getAllEmployees()).hasSize(2);
        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void getEmployeeTest() {
        Optional<Employee> optionalEmployee = employeeRepository.findById(1l);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            Assertions.assertThat(employee).isEqualTo(1);
        }
    }

}
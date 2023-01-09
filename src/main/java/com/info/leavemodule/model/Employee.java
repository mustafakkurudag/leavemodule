package com.info.leavemodule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificationNumber", nullable = false, length = 11)
    private String identificationNumber;

    @Column(name = "firstname", length = 20)
    private String firstName;

    @Column(name = "lastname", length = 20)
    private String lastName;

    @Column(name = "designation", length = 20)
    private String designation;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "advanceday")
    private int advanceDay;

    @Column(name = "remainingleaverights")
    private int remainingLeaveRights;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<Leave> leaves;

}

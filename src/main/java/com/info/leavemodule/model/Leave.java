package com.info.leavemodule.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "leaves")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;

    @Column(name = "leavestatus", nullable = false)
    private String leaveStatus;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}

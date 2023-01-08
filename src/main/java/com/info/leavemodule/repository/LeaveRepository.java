package com.info.leavemodule.repository;

import com.info.leavemodule.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    @Query(value = "SELECT l.* FROM Leaves l WHERE l.employee_id= :employee_id", nativeQuery = true)
    List<Leave> getLeavesByEmployee_Id(@Param(value = "employee_id") Long employee_id);
}

package com.example.Nestdigitalnewapp_backend.dao;

import com.example.Nestdigitalnewapp_backend.model.LeaveCount;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveCountDao extends CrudRepository<LeaveCount,Integer>{

    @Query(value = "SELECT `id`, `casual_leave`, `emp_id`, `sick_leave`, `special_leave`, `year` FROM `leave_count` WHERE `emp_id`=:emp_id",nativeQuery = true)
    List<LeaveCount> Leaves(@Param("emp_id")Integer emp_id);

    @Modifying
    @Transactional

    @Query(value = "UPDATE `leave_count` SET `casual_leave`=:casual_leave,`sick_leave`=:sick_leave,`special_leave`=:special_leave WHERE `emp_id`=:emp_id",nativeQuery = true)
    void UpdateCount (@Param("emp_id")Integer emp_id, @Param("casual_leave")Integer casual_leave, @Param("sick_leave")Integer sick_leave, @Param("special_leave")Integer special_leave);

    @Query(value = "SELECT `id`, `casual_leave`, `emp_id`, `sick_leave`, `special_leave`, `year` FROM `leave_count` WHERE `emp_id`=:emp_id",nativeQuery = true)
    List<LeaveCount> viewPendingLeave(@Param("emp_id")Integer emp_id);
}

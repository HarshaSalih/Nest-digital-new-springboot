package com.example.Nestdigitalnewapp_backend.dao;

import com.example.Nestdigitalnewapp_backend.model.Leave;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface LeaveDao extends CrudRepository<Leave,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leaves` SET `status`=:status WHERE `emp_id`=:emp_id",nativeQuery = true)
    void updateById(@Param("status") Integer status,@Param("emp_id") Integer emp_id);

//    @Query(value = "SELECT l.`id`, l.`apply_date`, l.`emp_id`, l.`from_date`, l.`remarks`, l.`status`, l.`to_date`,e.`id`,e.`name`,e.`designation` FROM `leaves` l JOIN employee e ON l.emp_id=e.id",nativeQuery = true)
//    List<Map<String,String>> viewAllLeave();

//    @Query(value = "SELECT l.`id`, l.`apply_date`, l.`emp_id`, l.`from_date`, l.`remarks`, l.`status`, l.`to_date`,l.`type`,e.`id`,e.`name`,e.`designation`  FROM `leaves` l JOIN employee e ON l.emp_id=e.id WHERE l.emp_id=:id",nativeQuery = true)
//    List<Map<String,String>> viewLeaveById(@Param("id")Integer id);
}

package com.example.Nestdigitalnewapp_backend.dao;


import com.example.Nestdigitalnewapp_backend.model.EmployeeLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeLogDao extends CrudRepository<EmployeeLog,Integer> {

    @Query(value = "SELECT `id`, `date`, `emp_id`, `enter_date_time`, `exit_date_time` FROM `employee_log` WHERE `date` =:date",nativeQuery = true)
    List<EmployeeLog> viewDailyEmployeeLog(@Param("date")String date);


}

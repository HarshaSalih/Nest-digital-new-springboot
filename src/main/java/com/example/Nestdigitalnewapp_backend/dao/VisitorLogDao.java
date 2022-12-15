package com.example.Nestdigitalnewapp_backend.dao;

import com.example.Nestdigitalnewapp_backend.model.VisitorLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitorLogDao extends CrudRepository<VisitorLog,Integer> {

    @Query(value = "SELECT `id`, `date`, `enter_date_time`, `exit_date_time`, `name`, `purpose`, `whom_to_meet` FROM `visitor_log` WHERE `date`=:date",nativeQuery = true)
    List<VisitorLog> ViewDailyVisitorLog(@Param("date") String date);


}

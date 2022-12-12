package com.example.Nestdigitalnewapp_backend.dao;

import com.example.Nestdigitalnewapp_backend.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.security.Security;
import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee,Integer> {

    @Query(value = "SELECT `id`, `confirm_password`, `designation`, `email`, `emp_code`, `name`, `password`, `phone`, `salary`, `username` FROM `employee` WHERE `username`=:username AND `password`=:password",nativeQuery = true)
    List<Employee> employeeLogin(@Param("username")String username, @Param("password")String password);

    @Query(value = "SELECT `id`, `confirm_password`, `designation`, `email`, `emp_code`, `name`, `password`, `phone`, `salary`, `username` FROM `employee` WHERE `emp_code`=:emp_code",nativeQuery = true)
    List<Employee> searchEmployee(@Param("emp_code") Integer emp_code);

    @Modifying
    @Transactional

    @Query(value = "DELETE FROM `employee` WHERE `id`=:id",nativeQuery = true)
    void deleteEmployee(@Param("id")Integer id);

    @Query(value = "SELECT `id`, `confirm_password`, `designation`, `email`, `emp_code`, `name`, `password`, `phone`, `salary`, `username` FROM `employee` WHERE `id`=:id",nativeQuery = true)
    List<Employee> viewProfile(@Param("id")Integer id);


}

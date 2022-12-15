package com.example.Nestdigitalnewapp_backend.controller;

import com.example.Nestdigitalnewapp_backend.dao.EmployeeDao;
import com.example.Nestdigitalnewapp_backend.dao.EmployeeLogDao;
import com.example.Nestdigitalnewapp_backend.dao.VisitorLogDao;
import com.example.Nestdigitalnewapp_backend.model.EmployeeLog;
import com.example.Nestdigitalnewapp_backend.model.VisitorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class EmployeeLogController {

    @Autowired
    private EmployeeLogDao edao;

    @Autowired
    private VisitorLogDao vdao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/logEmployee",consumes = "application/json",produces = "application/json")
    public Map<String,String> EmployLog(@RequestBody EmployeeLog emp)
    {

        edao.save(emp);
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewAllEmployeeLog")
    public List<EmployeeLog> ViewAllEmployeeLog()
    {
        return (List<EmployeeLog>) edao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewDailyEmployeeLog",consumes = "application/json",produces = "application/json")
    public List<EmployeeLog> viewDailyEmployeeLog(@RequestBody EmployeeLog emp)
    {
        return (List<EmployeeLog>) edao.viewDailyEmployeeLog(emp.getDate());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/logVisitor",consumes = "application/json",produces = "application/json")
    public Map<String,String> VisitorLog(@RequestBody VisitorLog v)
    {

        vdao.save(v);
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewAllVisitorLog")
    public List<VisitorLog> ViewAllVisitorLog()
    {
        return (List<VisitorLog>) vdao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewDailyVisitorLog",consumes = "application/json",produces = "application/json")
    public List<VisitorLog> ViewDailyVisitorLog(@RequestBody VisitorLog v)
    {
        return (List<VisitorLog>) vdao.ViewDailyVisitorLog(v.getDate());
    }






}

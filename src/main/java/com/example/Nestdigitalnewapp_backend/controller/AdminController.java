package com.example.Nestdigitalnewapp_backend.controller;

import com.example.Nestdigitalnewapp_backend.dao.EmployeeDao;
import com.example.Nestdigitalnewapp_backend.dao.SecurityDao;
import com.example.Nestdigitalnewapp_backend.model.Employee;
import com.example.Nestdigitalnewapp_backend.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController

public class AdminController {

    @Autowired
    private EmployeeDao dao;
    @Autowired
    private SecurityDao dao1;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/employeeLogin",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> employeeLogin(@RequestBody Employee e)
    {
        List<Employee> result=(List<Employee>) dao.employeeLogin(e.getUsername(),e.getPassword());
        HashMap<String,String> map=new HashMap<>();
        if (result.size()==0)
        {
            map.put("status","failed");
        }
        else
        {

            map.put("status","success");
            map.put("userId",String.valueOf(result.get(0).getId()));

        }
        return map;

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmployee",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> AddEmployee(@RequestBody Employee e)
    {
        HashMap<String,String> map=new HashMap<>();
        dao.save(e);
        map.put("id",String.valueOf(e.getId()));
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchEmployee",consumes = "application/json",produces = "application/json")
    public List<Employee> SearchEmployee(@RequestBody Employee e) {
        String empCode = String.valueOf(e.getEmpCode());
        System.out.println(empCode);
        return (List<Employee>) dao.searchEmployee(e.getEmpCode());

    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/deleteEmployee",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> DeleteEmployee(@RequestBody Employee e)
    {

        String id=String.valueOf(e.getId());
        System.out.println(id);
        dao.deleteEmployee(e.getId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewEmployee")
    public List<Employee> viewEmployee()
    {
        return (List<Employee>) dao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/viewProfile",consumes = "application/json",produces = "application/json")
    public List<Employee>viewProfile(@RequestBody Employee e)
    {
        return dao.viewProfile(e.getId());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/securityLogin",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> securityLogin(@RequestBody Security s)
    {
        List<Security> result=(List<Security>) dao1.securityLogin(s.getUsername(),s.getPassword());
        HashMap<String,String> map=new HashMap<>();
        if (result.size()==0)
        {
            map.put("status","failed");
        }
        else
        {

            map.put("status","success");
            map.put("userId",String.valueOf(result.get(0).getId()));

        }
        return map;

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addSecurity",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> AddSecurity(@RequestBody Security s)
    {
        HashMap<String,String> map=new HashMap<>();
        dao1.save(s);
        map.put("id",String.valueOf(s.getId()));
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchSecurity",consumes = "application/json",produces = "application/json")
    public List<Security> SearchSecurity(@RequestBody Security s) {
        String empCode = String.valueOf(s.getEmpCode());
        System.out.println(empCode);
        return (List<Security>) dao1.searchSecurity(s.getEmpCode());

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/deleteSecurity",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> DeleteSecurity(@RequestBody Security s)
    {

        String id=String.valueOf(s.getId());
        System.out.println(id);
        dao1.deleteSecurity(s.getId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewSecurity")
    public List<Security> viewSecurity()
    {
        return (List<Security>) dao1.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/viewSecurityProfile",consumes = "application/json",produces = "application/json")
    public List<Security>viewSecurityProfile(@RequestBody Security s)
    {
        return dao1.viewSecurityProfile((s.getId()));
    }

}

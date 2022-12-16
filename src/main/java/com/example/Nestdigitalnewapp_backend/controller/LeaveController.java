package com.example.Nestdigitalnewapp_backend.controller;

import com.example.Nestdigitalnewapp_backend.dao.LeaveDao;
import com.example.Nestdigitalnewapp_backend.model.Leave;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class LeaveController {

    @Autowired
    private LeaveDao ldao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addLeave", consumes = "application/json", produces = "application/json")
    public Map<String,String> addLeave(@RequestBody Leave l) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = String.valueOf((dt.format(now)));
        l.setApplyDate(currentDate);

        ldao.save(l);
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updateStatus", consumes = "application/json", produces = "application/json")
    public Map<String,String> updateStatus(@RequestBody Leave l) {
        ldao.updateById(l.getStatus(), l.getEmpId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

//    @CrossOrigin(origins = "*")
//    @GetMapping(path = "/viewAllLeave")
//    public List<Map<String,String>> viewAllLeave()
//    {
//        return (List<Map<String, String>>) ldao.viewAllLeave();
//    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewAllLeave")
    public List<Leave> viewAllLeave()
    {
        return (List<Leave>) ldao.findAll();
    }
}
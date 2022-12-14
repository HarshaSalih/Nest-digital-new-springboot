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
    public String addLeave(@RequestBody Leave l) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = String.valueOf((dt.format(now)));
        l.setApplyDate(currentDate);

        ldao.save(l);
        return "{status:'success'}";
    }


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updateStatus", consumes = "application/json", produces = "application/json")
    public Map<String,String> updateStatus(@RequestBody Leave l) {
        ldao.updateById(l.getStatus(), l.getId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllLeaves")
    public List<Leave> viewAllLeaves() {
        return (List<Leave>) ldao.findAll();

    }

//    @CrossOrigin(origins = "*")
//    @PostMapping(path = "/viewLeavesByEmpId",consumes = "application/json",produces = "application/json")
//    public  List<Map<String,String>> viewLeavesById(@RequestBody Leave l){
//        return (List<Map<String, String>>) ldao.viewLeaveById(l.getEmpId());
//    }
}
package com.example.Nestdigitalnewapp_backend.controller;

import com.example.Nestdigitalnewapp_backend.dao.LeaveCountDao;
import com.example.Nestdigitalnewapp_backend.dao.LeaveDao;
import com.example.Nestdigitalnewapp_backend.model.Leave;
import com.example.Nestdigitalnewapp_backend.model.LeaveCount;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class LeaveController {

    @Autowired
    private LeaveDao ldao;

    @Autowired
    private LeaveCountDao lcdao;
    int casualLeave,sickLeave,specialLeave;

    Date currentdate=new Date();

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

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/accept",consumes = "application/json",produces = "application/json")
    public Map<String,String> AcceptLeave(@RequestBody Leave l)
    {
        ldao.AcceptLeave(l.getEmpId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");

        return map;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchStatus",consumes = "application/json",produces = "application/json")
    public List<Leave> SearchStatus(@RequestBody Leave l)
    {
        String empId=String.valueOf(l.getEmpId());
        System.out.println(empId);
        return (List<Leave>) ldao.searchStatus(l.getEmpId());
    }


    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updatecounter",consumes = "application/json",produces = "application/json")
    public Map<String,String> UpdateCounter(@RequestBody Leave l) throws ParseException {
        String empId=String.valueOf(l.getEmpId());

        List<Leave> result1=(List<Leave>) ldao.searchStatus(l.getEmpId());
        l.setType(result1.get(0).getType());

        LocalDate from_date= LocalDate.parse(result1.get(0).getFromDate());
        LocalDate to_date=LocalDate.parse(result1.get(0).getToDate());

        long daysDiff= ChronoUnit.DAYS.between(from_date,to_date);
        System.out.println("no of days"+daysDiff);
        LeaveCount lc=new LeaveCount();

        List<LeaveCount> result=(List<LeaveCount>) lcdao.Leaves(l.getEmpId());
        casualLeave=result.get(0).getCasualLeave();
        sickLeave=result.get(0).getSickLeave();
        specialLeave=result.get(0).getSpecialLeave();



       if(l.getType().equalsIgnoreCase("casualLeave")&& daysDiff<=casualLeave){
           casualLeave=(int) (casualLeave-daysDiff);
           lc.setCasualLeave(casualLeave);
           sickLeave=sickLeave;
           specialLeave=specialLeave;

           lcdao.UpdateCount(l.getEmpId(),(int) casualLeave,(int) sickLeave,(int) specialLeave);

        } else if (l.getType().equalsIgnoreCase("sickLeave")&& daysDiff<=sickLeave) {
            casualLeave=casualLeave;
            sickLeave=(int) (sickLeave-daysDiff);
            lc.setSickLeave(sickLeave);
            specialLeave=specialLeave;

            lcdao.UpdateCount(l.getEmpId(),(int) casualLeave,(int) sickLeave,(int) specialLeave);
        }else if (l.getType().equalsIgnoreCase("specialLeave") && daysDiff<=specialLeave){
            casualLeave=casualLeave;
            sickLeave=sickLeave;
            specialLeave=(int) (specialLeave-daysDiff);
            lc.setSpecialLeave(specialLeave);

            lcdao.UpdateCount(l.getEmpId(),(int) casualLeave,(int) sickLeave,(int) specialLeave);
        }else {
            HashMap<String,String> map=new HashMap<>();
            map.put("leavetype",l.getType());
            String id=String.valueOf(result.get(0).getEmpId());
            map.put("empid",id);
            map.put("message","no leaves are available");
            return map;
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
   }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/viewPendingLeaves")
    public List<LeaveCount> ViewPendingLeaves(@RequestBody LeaveCount lc){
        String empId=String.valueOf(lc.getEmpId());
        System.out.println(empId);
        return (List<LeaveCount>) lcdao.viewPendingLeave(lc.getEmpId());
    }
}
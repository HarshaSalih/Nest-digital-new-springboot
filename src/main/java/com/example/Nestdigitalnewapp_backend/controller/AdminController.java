package com.example.Nestdigitalnewapp_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdminController {

    @GetMapping("/")
    public String EmployeeLogin()
    {
        return "Employeeloginpage";
    }
}

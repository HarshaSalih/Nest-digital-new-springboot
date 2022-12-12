package com.example.Nestdigitalnewapp_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leaves")

public class Leave {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int empId;
    private String type;
    private String remarks;
    private String fromDate;
    private String toDate;
    private String applyDate;
    private int status;

    public Leave() {
    }

    public Leave(int id, int empId, String type, String remarks, String fromDate, String toDate, String applyDate, int status) {
        this.id = id;
        this.empId = empId;
        this.type = type;
        this.remarks = remarks;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.applyDate = applyDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

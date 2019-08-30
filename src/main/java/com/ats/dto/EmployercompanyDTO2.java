package com.ats.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmployercompanyDTO2 {
    private int id;
    private int userId;
    private int companyId;
    private String status;
    private String userFullName;
    private String userEmail;
    private Timestamp createdDate;
}

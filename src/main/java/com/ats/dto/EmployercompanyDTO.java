package com.ats.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmployercompanyDTO {
    private int id;
    private int userId;
    private int companyId;
    private String status;
    private String companyStatus;
    private Timestamp createdDate;
}

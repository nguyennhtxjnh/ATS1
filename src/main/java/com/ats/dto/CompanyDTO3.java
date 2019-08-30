package com.ats.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CompanyDTO3 {
    private int id;
    private String nameCompany;
    private String usersEmail;
    private String companyEmail;
    private String telephoneNumber;
    private int cityId;
    private String cityName;
    private String address;
    private String email;
    private Timestamp createdDate;
    private String logoImg;
    private String description;
    private String status;

}

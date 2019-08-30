package com.ats.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ServicePackageDTO2 {
    private String name;
    private String status;
    private String duration;
    private String description;
    private Date createdDate;
    private Double price;
    private String functionName;
}

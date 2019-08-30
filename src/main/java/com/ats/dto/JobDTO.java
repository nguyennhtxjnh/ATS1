package com.ats.dto;

import lombok.Data;

@Data
public class JobDTO {
    private int id;
    private String title;
    private int companyId;
    private String companyName;
    private String companyLogoImg;
    private int cityId;
    private String cityName;
    private Double salaryTo;
    private Double salaryFrom;

}

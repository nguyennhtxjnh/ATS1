package com.ats.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CVMobileDTO implements Serializable {
    private int id;
    private String title;
    private int userId;
    private String fullName;
    private String cityName;
    private String industryName;
    private Integer isActive;
}

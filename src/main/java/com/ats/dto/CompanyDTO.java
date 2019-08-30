package com.ats.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CompanyDTO {
    private int id;
    @NotNull
    private String nameCompany;
    private int cityId;
    @NotNull
    private String address;
    @NotNull
    private String telephoneNumber;
    @NotNull
    private String email;
    private String logoImg;
    private String description;
    private Timestamp createdDate;
    private Timestamp lastModify;
    private String status;
    private List<CompanyindustryDTO> companyindustriesById;
}

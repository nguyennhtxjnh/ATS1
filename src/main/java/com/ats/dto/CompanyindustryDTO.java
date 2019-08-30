package com.ats.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyindustryDTO implements Serializable {
    private int id;
    private int companyId;
    private int industryId;
}

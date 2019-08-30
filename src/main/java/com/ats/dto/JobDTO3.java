package com.ats.dto;

import com.ats.entity.City;
import com.ats.entity.Company;
import com.ats.entity.Job;
import com.ats.enummerator.WorkingType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobDTO3 {
    private Integer id;
    private Integer userId;
    private Integer companyId;
    private Company company;
    private String title;
    private Integer cityId;
    private Integer industryId;
    private City city;
    private String address;
    private Integer joblevelId;
    private String joblevelName;
    private WorkingType workingtype;
    private Integer numberofrecruitment;
    private Double salaryFrom;
    private Double salaryTo;
    private Integer yearExperience;
    private Date createdDate;
    private Date endDateForApply;
    private String jobDescription;
    private String additionalRequest;
    private String candidateBenefits;
    private String status;
    private List<String> listSkillName;
    private List<Job> listJobSameCompany;
}

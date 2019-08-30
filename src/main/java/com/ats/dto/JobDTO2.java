package com.ats.dto;


import com.ats.enummerator.WorkingType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobDTO2 {
    private Integer id;
    private Integer userId;
    private Integer companyId;
    private Integer industryId;
    private String title;
    private Integer cityId;
    private String address;
    private Integer joblevelId;
    private WorkingType workingtype;
    private Integer numbeOfRecruitment;
    private Double salaryFrom;
    private Double salaryTo;
    private Integer yearExperience;
    private Date createdDate;
    private Date endDateForApply;
    private String jobDescription;
    private String additionalRequest;
    private String candidateBenefits;
    private String status;
    private List<SkillDTO> listSkill;

}

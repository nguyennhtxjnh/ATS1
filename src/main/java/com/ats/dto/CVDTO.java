package com.ats.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CVDTO implements Serializable {
    private int id;
    private String title;
    private int userId;
    private String img;
    private String email;
    private String telephoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Timestamp dob;
    private int cityId;
    private String address;
    private int industryId;
    private String description;
    private Integer yearExperience;
    private Double salaryFrom;
    private Double salaryTo;
    private String status;
    private Timestamp createdDate;
    private Timestamp lastModify;
    private Integer isActive;
    private List<CertificationDTO> certificationsById;
    private List<EducationDTO> educationsById;
    private List<ProjectorproductworkedDTO> projectorproductworkedsById;
    private List<SocialactivitiesDTO> socialactivitiesById;
    private List<WorkexperienceDTO> workexperiencesById;
    private List<ListSkillDTO> skillincvsById;
}

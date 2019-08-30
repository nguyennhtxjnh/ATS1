package com.ats.form;

import com.ats.dto.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CreateCVForm implements Serializable {
    private int id;
    private String title;
    @NotNull(message = "User ID can't null")
    private int userId;
    private MultipartFile img;
    @NotNull(message = "Email can't null!!!")
    private String email;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String telephoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Timestamp dob;
    @NotNull
    private int cityId;
    private String address;
    @NotNull
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
}

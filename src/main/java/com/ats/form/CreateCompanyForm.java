package com.ats.form;

import com.ats.dto.CompanyindustryDTO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CreateCompanyForm {
    private int id;
    private String nameCompany;
    private int userId;
    private int cityId;
    private String address;
    private String telephoneNumber;
    private String email;
    private MultipartFile logoImg;
    private String description;
    private Timestamp createdDate;
    private Timestamp lastModify;
    private String status;
    private List<CompanyindustryDTO> companyindustriesById;
}

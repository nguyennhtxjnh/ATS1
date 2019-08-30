package com.ats.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsersDTO2 {
    int id;
    String email;
    String password;
    String newPassword;
    String fullname;
    String status;
    Date createdDate;
    Date lastLogin;
    Date lastModify;
    int roleId;
    String accessToken;
    String telephoneNumber;
    String gender;
    int jobLevelID;
    String vacancyName;
    int cityid;
    String address;
    String description;
    List<JobDTO3> listOfJob;
}

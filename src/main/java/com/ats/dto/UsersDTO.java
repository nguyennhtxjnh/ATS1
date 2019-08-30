package com.ats.dto;

import lombok.Data;

import java.util.Date;


@Data
public class UsersDTO{
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


    public UsersDTO() {
        super();
    }


    public UsersDTO(int id, String fullname, String email, int roleId, String accessToken) {
        super();
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.roleId = roleId;
        this.accessToken = accessToken;
    }


    public UsersDTO(int id, String email, String password, String newPassword, String fullname,
                    String status, Date createdDate, Date lastLogin, Date lastModify,
                    int roleId, String accessToken, String telephoneNumber, String gender,
                    int jobLevelID, String vacancyName, int cityid, String address, String description) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.newPassword = newPassword;
        this.fullname = fullname;
        this.status = status;
        this.createdDate = createdDate;
        this.lastLogin = lastLogin;
        this.lastModify = lastModify;
        this.roleId = roleId;
        this.accessToken = accessToken;
        this.telephoneNumber = telephoneNumber;
        this.gender = gender;
        this.jobLevelID = jobLevelID;
        this.vacancyName = vacancyName;
        this.cityid = cityid;
        this.address = address;
        this.description = description;
    }

}

package com.ats.dto;

import com.ats.entity.Cv;
import com.ats.entity.Users;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UserslikecvDTO implements Serializable {
    private int id;
    private int userId;
    private int cvid;
    private Timestamp createdDate;
    private Timestamp lastModifyDate;
    private Integer isActive;
    private Users usersByUserId;
    private Cv cvByCvid;
}

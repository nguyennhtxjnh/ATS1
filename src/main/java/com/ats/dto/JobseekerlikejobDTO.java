package com.ats.dto;

import com.ats.entity.Job;
import com.ats.entity.Users;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class JobseekerlikejobDTO {
    private int id;
    private int userId;
    private int jobId;
    private Timestamp createdDate;
    private Timestamp lastModifyDate;
    private Integer isActive;
    private Users usersByUserId;
    private Job jobByJobId;
}

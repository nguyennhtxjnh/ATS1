package com.ats.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FeedBackDTO2 {
    private Integer feedBackTypeId;
    private String companyName;
    private String jobName;
    private Integer jobId;
    private Integer userId;
    private String description;
    private Date createdDate;
    private Integer isReply;
}

package com.ats.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FeedBackDTO {
    private int id;
    private Integer feedBackTypeId;
    private Integer jobId;
    private Integer userId;
    private String description;
    private Date createdDate;
    private int isReply;
}

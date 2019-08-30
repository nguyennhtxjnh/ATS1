package com.ats.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CountjobDTO {
    private int id;
    private int userId;
    private int jobId;
    private Timestamp createdDate;
}

package com.ats.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CountcvDTO {
    private int id;
    private int userId;
    private int cvid;
    private Timestamp createdDate;
}

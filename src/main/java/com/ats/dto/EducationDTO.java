package com.ats.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class EducationDTO implements Serializable {
    private int id;
    private int cvid;
    private String schoolType;
    private String schoolName;
    private String major;
    private Timestamp startTime;
    private Timestamp endtime;
    private String description;

    public EducationDTO() {
    }

    public EducationDTO(int id, int cvid, String schoolType, String schoolName, String major, Timestamp startTime, Timestamp endtime, String description) {
        this.id = id;
        this.cvid = cvid;
        this.schoolType = schoolType;
        this.schoolName = schoolName;
        this.major = major;
        this.startTime = startTime;
        this.endtime = endtime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

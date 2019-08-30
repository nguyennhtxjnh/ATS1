package com.ats.dto;

import java.sql.Timestamp;

public class WorkexperienceDTO {
    private int id;
    private int cvid;
    private String companyName;
    private String vacancyName;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;

    public WorkexperienceDTO() {
    }

    public WorkexperienceDTO(int id, int cvid, String companyName, String vacancyName, Timestamp startTime, Timestamp endTime, String description) {
        this.id = id;
        this.cvid = cvid;
        this.companyName = companyName;
        this.vacancyName = vacancyName;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

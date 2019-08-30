package com.ats.dto;

import java.sql.Timestamp;

public class ProjectorproductworkedDTO {
    private int id;
    private int cvid;
    private String projetName;
    private String vacancyName;
    private Timestamp startTime;
    private Timestamp endTime;
    private String skillUsed;
    private String description;

    public ProjectorproductworkedDTO() {
    }

    public ProjectorproductworkedDTO(int id, int cvid, String projetName, String vacancyName, Timestamp startTime, Timestamp endTime, String skillUsed, String description) {
        this.id = id;
        this.cvid = cvid;
        this.projetName = projetName;
        this.vacancyName = vacancyName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.skillUsed = skillUsed;
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

    public String getProjetName() {
        return projetName;
    }

    public void setProjetName(String projetName) {
        this.projetName = projetName;
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

    public String getSkillUsed() {
        return skillUsed;
    }

    public void setSkillUsed(String skillUsed) {
        this.skillUsed = skillUsed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

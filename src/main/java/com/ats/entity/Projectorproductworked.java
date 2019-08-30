package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Projectorproductworked {
    private int id;
    private int cvid;
    private String projetName;
    private String vacancyName;
    private Timestamp startTime;
    private Timestamp endTime;
    private String skillUsed;
    private String description;
    private Cv cvByCvid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CVID", nullable = false , insertable = false , updatable = false)
    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    @Basic
    @Column(name = "ProjetName", nullable = true, length = 100)
    public String getProjetName() {
        return projetName;
    }

    public void setProjetName(String projetName) {
        this.projetName = projetName;
    }

    @Basic
    @Column(name = "VacancyName", nullable = true, length = 50)
    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    @Basic
    @Column(name = "StartTime", nullable = true)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "EndTime", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "SkillUsed", nullable = true, length = 200)
    public String getSkillUsed() {
        return skillUsed;
    }

    public void setSkillUsed(String skillUsed) {
        this.skillUsed = skillUsed;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projectorproductworked that = (Projectorproductworked) o;
        return id == that.id &&
                cvid == that.cvid &&
                Objects.equals(projetName, that.projetName) &&
                Objects.equals(vacancyName, that.vacancyName) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(skillUsed, that.skillUsed) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvid, projetName, vacancyName, startTime, endTime, skillUsed, description);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "CVID", referencedColumnName = "ID", nullable = false)
    public Cv getCvByCvid() {
        return cvByCvid;
    }

    public void setCvByCvid(Cv cvByCvid) {
        this.cvByCvid = cvByCvid;
    }
}

package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Education {
    private int id;
    private int cvid;
    private String schoolType;
    private String schoolName;
    private String major;
    private Timestamp startTime;
    private Timestamp endtime;
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
    @Column(name = "SchoolType", nullable = true, length = 50)
    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    @Basic
    @Column(name = "SchoolName", nullable = true, length = 50)
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "Major", nullable = true, length = 50)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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
    @Column(name = "Endtime", nullable = true)
    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
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
        Education education = (Education) o;
        return id == education.id &&
                cvid == education.cvid &&
                Objects.equals(schoolType, education.schoolType) &&
                Objects.equals(schoolName, education.schoolName) &&
                Objects.equals(major, education.major) &&
                Objects.equals(startTime, education.startTime) &&
                Objects.equals(endtime, education.endtime) &&
                Objects.equals(description, education.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvid, schoolType, schoolName, major, startTime, endtime, description);
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

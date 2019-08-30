package com.ats.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Apply {
    private int id;
    private int jobId;
    private Timestamp dayApply;
    private String status;
    private int cvid;
    private Job jobByJobId;
    private Cv cvByCvid;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JobID", nullable = false, insertable = false , updatable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "DayApply", nullable = true)
    public Timestamp getDayApply() {
        return dayApply;
    }

    public void setDayApply(Timestamp dayApply) {
        this.dayApply = dayApply;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CVID", nullable = false, insertable = false , updatable = false)
    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return id == apply.id &&
                jobId == apply.jobId &&
                cvid == apply.cvid &&
                Objects.equals(dayApply, apply.dayApply) &&
                Objects.equals(status, apply.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobId, dayApply, status, cvid);
    }


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "JobID", referencedColumnName = "ID", nullable = false)
    public Job getJobByJobId() {
        return jobByJobId;
    }

    public void setJobByJobId(Job jobByJobId) {
        this.jobByJobId = jobByJobId;
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

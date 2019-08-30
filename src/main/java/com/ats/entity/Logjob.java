package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Logjob {
    private int id;
    private int jobId;
    private int curatorId;
    private int logType;
    private Timestamp createdDate;
    private Job jobByJobId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JobID", nullable = false , insertable = false , updatable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "CuratorID", nullable = false)
    public int getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(int curatorId) {
        this.curatorId = curatorId;
    }

    @Basic
    @Column(name = "LogType", nullable = false)
    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logjob logjob = (Logjob) o;
        return id == logjob.id &&
                jobId == logjob.jobId &&
                curatorId == logjob.curatorId &&
                logType == logjob.logType &&
                Objects.equals(createdDate, logjob.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobId, curatorId, logType, createdDate);
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
}

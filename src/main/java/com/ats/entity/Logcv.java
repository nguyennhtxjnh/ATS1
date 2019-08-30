package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Logcv {
    private int id;
    private int cvid;
    private int curatorId;
    private int logType;
    private Timestamp createdDate;
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
    @Column(name = "CVID", nullable = false , insertable = false , updatable = false)
    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
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
        Logcv logcv = (Logcv) o;
        return id == logcv.id &&
                cvid == logcv.cvid &&
                curatorId == logcv.curatorId &&
                logType == logcv.logType &&
                Objects.equals(createdDate, logcv.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvid, curatorId, logType, createdDate);
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

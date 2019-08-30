package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Logusers {
    private int id;
    private int userId;
    private int curatorId;
    private int logType;
    private Timestamp createDate;
    private Users usersByUserId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID", nullable = false , insertable = false , updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "CreateDate", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logusers logusers = (Logusers) o;
        return id == logusers.id &&
                userId == logusers.userId &&
                curatorId == logusers.curatorId &&
                logType == logusers.logType &&
                Objects.equals(createDate, logusers.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, curatorId, logType, createDate);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}

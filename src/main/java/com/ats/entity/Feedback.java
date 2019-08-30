package com.ats.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Feedback {
    private int id;
    private Integer feedBackTypeId;
    private Integer jobId;
    private Integer userId;
    private String description;
    private Date createdDate;
    private Integer isReply;
    private Feedbacktype feedbacktypeByFeedBackTypeId;
    private Job jobByJobId;
    private Users usersByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FeedBackTypeID", nullable = true, insertable = false, updatable = false)
    public Integer getFeedBackTypeId() {
        return feedBackTypeId;
    }

    public void setFeedBackTypeId(Integer feedBackTypeId) {
        this.feedBackTypeId = feedBackTypeId;
    }

    @Basic
    @Column(name = "JobID", nullable = true, insertable = false, updatable = false)
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "UserID", nullable = true, insertable = false, updatable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "isReply", nullable = true)
    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id == feedback.id &&
                Objects.equals(feedBackTypeId, feedback.feedBackTypeId) &&
                Objects.equals(jobId, feedback.jobId) &&
                Objects.equals(userId, feedback.userId) &&
                Objects.equals(description, feedback.description) &&
                Objects.equals(createdDate, feedback.createdDate) &&
                Objects.equals(isReply, feedback.isReply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedBackTypeId, jobId, userId, description, createdDate,isReply);
    }

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "FeedBackTypeID", referencedColumnName = "id")
    public Feedbacktype getFeedbacktypeByFeedBackTypeId() {
        return feedbacktypeByFeedBackTypeId;
    }

    public void setFeedbacktypeByFeedBackTypeId(Feedbacktype feedbacktypeByFeedBackTypeId) {
        this.feedbacktypeByFeedBackTypeId = feedbacktypeByFeedBackTypeId;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "JobID", referencedColumnName = "ID")
    public Job getJobByJobId() {
        return jobByJobId;
    }

    public void setJobByJobId(Job jobByJobId) {
        this.jobByJobId = jobByJobId;
    }

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}

package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Joblevel {
    private int id;
    private String jobLevelName;
    private List<Job> jobsById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JobLevelName", nullable = true, length = 50)
    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joblevel joblevel = (Joblevel) o;
        return id == joblevel.id &&
                Objects.equals(jobLevelName, joblevel.jobLevelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobLevelName);
    }

    @OneToMany(mappedBy = "joblevelByJobLevelId")
    @JsonBackReference
    public List<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(List<Job> jobsById) {
        this.jobsById = jobsById;
    }
}

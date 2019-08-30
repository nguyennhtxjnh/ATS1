package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Industry {
    private int id;
    private String name;
    private List<Companyindustry> companyindustriesById;
    private List<Cv> cvsById;
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
    @Column(name = "Name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Industry industry = (Industry) o;
        return id == industry.id &&
                Objects.equals(name, industry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "industryByIndustryId")
    @JsonBackReference
    public List<Companyindustry> getCompanyindustriesById() {
        return companyindustriesById;
    }

    public void setCompanyindustriesById(List<Companyindustry> companyindustriesById) {
        this.companyindustriesById = companyindustriesById;
    }

    @OneToMany(mappedBy = "industryByIndustryId")
    @JsonBackReference
    public List<Cv> getCvsById() {
        return cvsById;
    }

    public void setCvsById(List<Cv> cvsById) {
        this.cvsById = cvsById;
    }

    @OneToMany(mappedBy = "industryByIndustryId")
    @JsonBackReference
    public List<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(List<Job> jobsById) {
        this.jobsById = jobsById;
    }
}

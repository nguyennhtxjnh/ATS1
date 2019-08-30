package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Companyindustry {
    private int id;
    private int companyId;
    private int industryId;
    private Company companyByCompanyId;
    private Industry industryByIndustryId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CompanyID", nullable = false , insertable = false , updatable = false)
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "IndustryID", nullable = false , insertable = false , updatable = false)
    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companyindustry that = (Companyindustry) o;
        return id == that.id &&
                companyId == that.companyId &&
                industryId == that.industryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyId, industryId);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "CompanyID", referencedColumnName = "ID", nullable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "IndustryID", referencedColumnName = "ID", nullable = false)
    public Industry getIndustryByIndustryId() {
        return industryByIndustryId;
    }

    public void setIndustryByIndustryId(Industry industryByIndustryId) {
        this.industryByIndustryId = industryByIndustryId;
    }
}

package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Receipts {
    private int id;
    private int servicePackageId;
    private Integer employerId;
    private Timestamp paiedDay;
    private Servicepackage servicepackageByServicePackageId;
    private Users usersByEmployerId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ServicePackageID", nullable = false , insertable = false , updatable = false)
    public int getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(int servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    @Basic
    @Column(name = "EmployerID", nullable = true , insertable = false , updatable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    @Basic
    @Column(name = "PaiedDay", nullable = true)
    public Timestamp getPaiedDay() {
        return paiedDay;
    }

    public void setPaiedDay(Timestamp paiedDay) {
        this.paiedDay = paiedDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipts receipts = (Receipts) o;
        return id == receipts.id &&
                servicePackageId == receipts.servicePackageId &&
                Objects.equals(employerId, receipts.employerId) &&
                Objects.equals(paiedDay, receipts.paiedDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servicePackageId, employerId, paiedDay);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ServicePackageID", referencedColumnName = "ID", nullable = false)
    public Servicepackage getServicepackageByServicePackageId() {
        return servicepackageByServicePackageId;
    }

    public void setServicepackageByServicePackageId(Servicepackage servicepackageByServicePackageId) {
        this.servicepackageByServicePackageId = servicepackageByServicePackageId;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "EmployerID", referencedColumnName = "ID")
    public Users getUsersByEmployerId() {
        return usersByEmployerId;
    }

    public void setUsersByEmployerId(Users usersByEmployerId) {
        this.usersByEmployerId = usersByEmployerId;
    }
}

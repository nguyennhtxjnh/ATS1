package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Users implements Serializable {
    private int id;
    private String email;
    private String fullName;
    private String password;
    private String accessToken;
    private int roleId;
    private String telephoneNumber;
    private String gender;
    private Integer jobLevelId;
    private String vacancyName;
    private Integer cityId;
    private String address;
    private String description;
    private Timestamp createdDate;
    private Timestamp lastLogin;
    private Timestamp lastModify;
    private String status;
    private List<Countcv> countcvsById;
    private List<Countjob> countjobsById;
    private List<Cv> cvsById;
    private List<Employercompany> employercompaniesById;
    private List<Job> jobsById;
    private List<Jobseekerlikejob> jobseekerlikejobsById;
    private List<Logusers> logusersById;
    private List<Receipts> receiptsById;
    private List<Userslikecv> userslikecvsById;
    private List<Feedback> feedbacksById;

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
    @Column(name = "Email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "FullName", nullable = true, length = 50)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "Password", nullable = true, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "AccessToken", nullable = true, length = 200)
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Basic
    @Column(name = "RoleId", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "TelephoneNumber", nullable = true, length = 10)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "Gender", nullable = true, length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "JobLevelID", nullable = true)
    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    @Basic
    @Column(name = "VacancyName", nullable = true, length = 50)
    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    @Basic
    @Column(name = "CityID", nullable = true)
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "Address", nullable = true, length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "LastLogin", nullable = true)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "LastModify", nullable = true)
    public Timestamp getLastModify() {
        return lastModify;
    }

    public void setLastModify(Timestamp lastModify) {
        this.lastModify = lastModify;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                roleId == users.roleId &&
                Objects.equals(email, users.email) &&
                Objects.equals(fullName, users.fullName) &&
                Objects.equals(password, users.password) &&
                Objects.equals(accessToken, users.accessToken) &&
                Objects.equals(telephoneNumber, users.telephoneNumber) &&
                Objects.equals(gender, users.gender) &&
                Objects.equals(jobLevelId, users.jobLevelId) &&
                Objects.equals(vacancyName, users.vacancyName) &&
                Objects.equals(cityId, users.cityId) &&
                Objects.equals(address, users.address) &&
                Objects.equals(description, users.description) &&
                Objects.equals(createdDate, users.createdDate) &&
                Objects.equals(lastLogin, users.lastLogin) &&
                Objects.equals(lastModify, users.lastModify) &&
                Objects.equals(status, users.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, fullName, password, accessToken, roleId, telephoneNumber, gender, jobLevelId, vacancyName, cityId, address, description, createdDate, lastLogin, lastModify, status);
    }


    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Countcv> getCountcvsById() {
        return countcvsById;
    }

    public void setCountcvsById(List<Countcv> countcvsById) {
        this.countcvsById = countcvsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Countjob> getCountjobsById() {
        return countjobsById;
    }

    public void setCountjobsById(List<Countjob> countjobsById) {
        this.countjobsById = countjobsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Cv> getCvsById() {
        return cvsById;
    }

    public void setCvsById(List<Cv> cvsById) {
        this.cvsById = cvsById;
    }


    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Employercompany> getEmployercompaniesById() {
        return employercompaniesById;
    }

    public void setEmployercompaniesById(List<Employercompany> employercompaniesById) {
        this.employercompaniesById = employercompaniesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(List<Job> jobsById) {
        this.jobsById = jobsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Jobseekerlikejob> getJobseekerlikejobsById() {
        return jobseekerlikejobsById;
    }

    public void setJobseekerlikejobsById(List<Jobseekerlikejob> jobseekerlikejobsById) {
        this.jobseekerlikejobsById = jobseekerlikejobsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Logusers> getLogusersById() {
        return logusersById;
    }

    public void setLogusersById(List<Logusers> logusersById) {
        this.logusersById = logusersById;
    }

    @OneToMany(mappedBy = "usersByEmployerId")
    @JsonBackReference
    public List<Receipts> getReceiptsById() {
        return receiptsById;
    }

    public void setReceiptsById(List<Receipts> receiptsById) {
        this.receiptsById = receiptsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Userslikecv> getUserslikecvsById() {
        return userslikecvsById;
    }

    public void setUserslikecvsById(List<Userslikecv> userslikecvsById) {
        this.userslikecvsById = userslikecvsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonBackReference
    public List<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(List<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }


}

package com.ats.entity;

import com.ats.enummerator.WorkingType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Job {
    private int id;
    private int userId;
    private int companyId;
    private String title;
    private int cityId;
    private String address;
    private int jobLevelId;
    private WorkingType workingType;
    private Integer numbeOfRecruitment;
    private Double salaryFrom;
    private Double salaryTo;
    private Integer yearExperience;
    private Timestamp createdDate;
    private Timestamp endDateForApply;
    private String jobDescription;
    private String additionalRequest;
    private String candidateBenefits;
    private String status;
    private int industryId;
    private List<Apply> appliesById;
    private List<Countjob> countjobsById;
    private Users usersByUserId;
    private Company companyByCompanyId;
    private City cityByCityId;
    private Joblevel joblevelByJobLevelId;
    private Industry industryByIndustryId;
    private List<Jobseekerlikejob> jobseekerlikejobsById;
    private List<Logjob> logjobsById;
    private List<Skillneedforjob> skillneedforjobsById;
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
    @Column(name = "UserID", nullable = false, insertable = false , updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CompanyID", nullable = false, insertable = false , updatable = false)
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "Title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "CityID", nullable = false , insertable = false , updatable = false)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
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
    @Column(name = "JobLevelID", nullable = false, insertable = false , updatable = false)
    public int getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(int jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "WorkingType", nullable = true)
    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
    }

    @Basic
    @Column(name = "NumbeOfRecruitment", nullable = true)
    public Integer getNumbeOfRecruitment() {
        return numbeOfRecruitment;
    }

    public void setNumbeOfRecruitment(Integer numbeOfRecruitment) {
        this.numbeOfRecruitment = numbeOfRecruitment;
    }

    @Basic
    @Column(name = "SalaryFrom", nullable = true, precision = 0)
    public Double getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Double salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    @Basic
    @Column(name = "SalaryTo", nullable = true, precision = 0)
    public Double getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Double salaryTo) {
        this.salaryTo = salaryTo;
    }

    @Basic
    @Column(name = "YearExperience", nullable = true)
    public Integer getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(Integer yearExperience) {
        this.yearExperience = yearExperience;
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
    @Column(name = "EndDateForApply", nullable = true)
    public Timestamp getEndDateForApply() {
        return endDateForApply;
    }

    public void setEndDateForApply(Timestamp endDateForApply) {
        this.endDateForApply = endDateForApply;
    }

    @Basic
    @Column(name = "JobDescription", nullable = true, length = 50)
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Basic
    @Column(name = "AdditionalRequest", nullable = true, length = 50)
    public String getAdditionalRequest() {
        return additionalRequest;
    }

    public void setAdditionalRequest(String additionalRequest) {
        this.additionalRequest = additionalRequest;
    }

    @Basic
    @Column(name = "CandidateBenefits", nullable = true, length = 50)
    public String getCandidateBenefits() {
        return candidateBenefits;
    }

    public void setCandidateBenefits(String candidateBenefits) {
        this.candidateBenefits = candidateBenefits;
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
    @Column(name = "IndustryID", nullable = false, insertable = false , updatable = false)
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
        Job job = (Job) o;
        return id == job.id &&
                userId == job.userId &&
                companyId == job.companyId &&
                cityId == job.cityId &&
                jobLevelId == job.jobLevelId &&
                industryId == job.industryId &&
                Objects.equals(title, job.title) &&
                Objects.equals(address, job.address) &&
                Objects.equals(workingType, job.workingType) &&
                Objects.equals(numbeOfRecruitment, job.numbeOfRecruitment) &&
                Objects.equals(salaryFrom, job.salaryFrom) &&
                Objects.equals(salaryTo, job.salaryTo) &&
                Objects.equals(yearExperience, job.yearExperience) &&
                Objects.equals(createdDate, job.createdDate) &&
                Objects.equals(endDateForApply, job.endDateForApply) &&
                Objects.equals(jobDescription, job.jobDescription) &&
                Objects.equals(additionalRequest, job.additionalRequest) &&
                Objects.equals(candidateBenefits, job.candidateBenefits) &&
                Objects.equals(status, job.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, companyId, title, cityId, address, jobLevelId, workingType, numbeOfRecruitment, salaryFrom, salaryTo, yearExperience, createdDate, endDateForApply, jobDescription, additionalRequest, candidateBenefits, status, industryId);
    }

    @OneToMany(mappedBy = "jobByJobId")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Apply> getAppliesById() {
        return appliesById;
    }

    public void setAppliesById(List<Apply> appliesById) {
        this.appliesById = appliesById;
    }

    @OneToMany(mappedBy = "jobByJobId")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Countjob> getCountjobsById() {
        return countjobsById;
    }

    public void setCountjobsById(List<Countjob> countjobsById) {
        this.countjobsById = countjobsById;
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

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "CompanyID", referencedColumnName = "ID", nullable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "CityID", referencedColumnName = "ID", nullable = false)
    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "JobLevelID", referencedColumnName = "ID", nullable = false)
    public Joblevel getJoblevelByJobLevelId() {
        return joblevelByJobLevelId;
    }

    public void setJoblevelByJobLevelId(Joblevel joblevelByJobLevelId) {
        this.joblevelByJobLevelId = joblevelByJobLevelId;
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

    @OneToMany(mappedBy = "jobByJobId")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Jobseekerlikejob> getJobseekerlikejobsById() {
        return jobseekerlikejobsById;
    }

    public void setJobseekerlikejobsById(List<Jobseekerlikejob> jobseekerlikejobsById) {
        this.jobseekerlikejobsById = jobseekerlikejobsById;
    }

    @OneToMany(mappedBy = "jobByJobId")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Logjob> getLogjobsById() {
        return logjobsById;
    }

    public void setLogjobsById(List<Logjob> logjobsById) {
        this.logjobsById = logjobsById;
    }

    @OneToMany(mappedBy = "jobByJobId")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Skillneedforjob> getSkillneedforjobsById() {
        return skillneedforjobsById;
    }

    public void setSkillneedforjobsById(List<Skillneedforjob> skillneedforjobsById) {
        this.skillneedforjobsById = skillneedforjobsById;
    }

    @OneToMany(mappedBy = "jobByJobId")
    @JsonBackReference
    public List<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(List<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

}

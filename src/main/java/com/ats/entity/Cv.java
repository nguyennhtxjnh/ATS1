package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Cv {
    private int id;
    private String title;
    private int userId;
    private String img;
    private String email;
    private String telephoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Timestamp dob;
    private int cityId;
    private String address;
    private int industryId;
    private String description;
    private Integer yearExperience;
    private Double salaryFrom;
    private Double salaryTo;
    private String status;
    private Timestamp createdDate;
    private Timestamp lastModify;
    private Integer isActive;
    private List<Apply> appliesById;
    private List<Certification> certificationsById;
    private List<Countcv> countcvsById;
    private Users usersByUserId;
    private City cityByCityId;
    private Industry industryByIndustryId;
    private List<Education> educationsById;
    private List<Logcv> logcvsById;
    private List<Projectorproductworked> projectorproductworkedsById;
    private List<Skillincv> skillincvsById;
    private List<Socialactivities> socialactivitiesById;
    private List<Userslikecv> userslikecvsById;
    private List<Workexperience> workexperiencesById;

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
    @Column(name = "Title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "Img", nullable = true, length = -1)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
    @Column(name = "TelephoneNumber", nullable = true, length = 15)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "FirstName", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    @Column(name = "DOB", nullable = true)
    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
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
    @Column(name = "IndustryID", nullable = false , insertable = false , updatable = false)
    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
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
    @Column(name = "YearExperience", nullable = true)
    public Integer getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(Integer yearExperience) {
        this.yearExperience = yearExperience;
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
    @Column(name = "Status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "LastModify", nullable = true)
    public Timestamp getLastModify() {
        return lastModify;
    }

    public void setLastModify(Timestamp lastModify) {
        this.lastModify = lastModify;
    }

    @Basic
    @Column(name = "isActive", nullable = true)
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cv cv = (Cv) o;
        return id == cv.id &&
                userId == cv.userId &&
                cityId == cv.cityId &&
                industryId == cv.industryId &&
                Objects.equals(title, cv.title) &&
                Objects.equals(img, cv.img) &&
                Objects.equals(email, cv.email) &&
                Objects.equals(telephoneNumber, cv.telephoneNumber) &&
                Objects.equals(firstName, cv.firstName) &&
                Objects.equals(lastName, cv.lastName) &&
                Objects.equals(gender, cv.gender) &&
                Objects.equals(dob, cv.dob) &&
                Objects.equals(address, cv.address) &&
                Objects.equals(description, cv.description) &&
                Objects.equals(yearExperience, cv.yearExperience) &&
                Objects.equals(salaryFrom, cv.salaryFrom) &&
                Objects.equals(salaryTo, cv.salaryTo) &&
                Objects.equals(status, cv.status) &&
                Objects.equals(createdDate, cv.createdDate) &&
                Objects.equals(lastModify, cv.lastModify) &&
                Objects.equals(isActive, cv.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, userId, img, email, telephoneNumber, firstName, lastName, gender, dob, cityId, address, industryId, description, yearExperience, salaryFrom, salaryTo, status, createdDate, lastModify, isActive);
    }

    public void setAppliesById(List<Apply> appliesById) {
        this.appliesById = appliesById;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cvByCvid")
    public List<Certification> getCertificationsById() {
        return certificationsById;
    }

    public void setCertificationsById(List<Certification> certificationsById) {
        this.certificationsById = certificationsById;
    }

    @OneToMany(mappedBy = "cvByCvid")
    @JsonBackReference
    public List<Countcv> getCountcvsById() {
        return countcvsById;
    }

    public void setCountcvsById(List<Countcv> countcvsById) {
        this.countcvsById = countcvsById;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "CityID", referencedColumnName = "ID")
    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "IndustryID", referencedColumnName = "ID", nullable = false)
    public Industry getIndustryByIndustryId() {
        return industryByIndustryId;
    }

    public void setIndustryByIndustryId(Industry industryByIndustryId) {
        this.industryByIndustryId = industryByIndustryId;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cvByCvid")
    public List<Education> getEducationsById() {
        return educationsById;
    }

    public void setEducationsById(List<Education> educationsById) {
        this.educationsById = educationsById;
    }

    @OneToMany(mappedBy = "cvByCvid")
    @JsonBackReference
    public List<Logcv> getLogcvsById() {
        return logcvsById;
    }

    public void setLogcvsById(List<Logcv> logcvsById) {
        this.logcvsById = logcvsById;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cvByCvid")
    public List<Projectorproductworked> getProjectorproductworkedsById() {
        return projectorproductworkedsById;
    }

    public void setProjectorproductworkedsById(List<Projectorproductworked> projectorproductworkedsById) {
        this.projectorproductworkedsById = projectorproductworkedsById;
    }

    @OneToMany(mappedBy = "cvByCvid")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Skillincv> getSkillincvsById() {
        return skillincvsById;
    }

    public void setSkillincvsById(List<Skillincv> skillincvsById) {
        this.skillincvsById = skillincvsById;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cvByCvid")
    public List<Socialactivities> getSocialactivitiesById() {
        return socialactivitiesById;
    }

    public void setSocialactivitiesById(List<Socialactivities> socialactivitiesById) {
        this.socialactivitiesById = socialactivitiesById;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "cvByCvid")
    public List<Userslikecv> getUserslikecvsById() {
        return userslikecvsById;
    }

    public void setUserslikecvsById(List<Userslikecv> userslikecvsById) {
        this.userslikecvsById = userslikecvsById;
    }


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cvByCvid")
    public List<Workexperience> getWorkexperiencesById() {
        return workexperiencesById;
    }

    public void setWorkexperiencesById(List<Workexperience> workexperiencesById) {
        this.workexperiencesById = workexperiencesById;
    }
}

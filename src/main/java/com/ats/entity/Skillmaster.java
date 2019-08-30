package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Skillmaster {
    private int id;
    private int skillTypeId;
    private String skillName;
    private List<Skill> skillsById;
    private Skilltype skilltypeBySkillTypeId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SkillTypeID", nullable = false , insertable = false , updatable = false)
    public int getSkillTypeId() {
        return skillTypeId;
    }

    public void setSkillTypeId(int skillTypeId) {
        this.skillTypeId = skillTypeId;
    }

    @Basic
    @Column(name = "SkillName", nullable = true, length = 100)
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skillmaster that = (Skillmaster) o;
        return id == that.id &&
                skillTypeId == that.skillTypeId &&
                Objects.equals(skillName, that.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillTypeId, skillName);
    }


    @OneToMany(mappedBy = "skillmasterBySkillMasterId")
    @JsonBackReference
    public List<Skill> getSkillsById() {
        return skillsById;
    }

    public void setSkillsById(List<Skill> skillsById) {
        this.skillsById = skillsById;
    }

    @JsonBackReference(value="SkillType")
    @ManyToOne
    @JoinColumn(name = "SkillTypeID", referencedColumnName = "ID", nullable = false)
    public Skilltype getSkilltypeBySkillTypeId() {
        return skilltypeBySkillTypeId;
    }

    public void setSkilltypeBySkillTypeId(Skilltype skilltypeBySkillTypeId) {
        this.skilltypeBySkillTypeId = skilltypeBySkillTypeId;
    }
}

package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Skill implements Serializable {
    private int id;
    private int skillMasterId;
    private Integer skillLevel;
    private Skillmaster skillmasterBySkillMasterId;
    private List<Skillincv> skillincvsById;
    private List<Skillneedforjob> skillneedforjobsById;

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
    @Column(name = "SkillMasterID", nullable = false , insertable = false , updatable = false)
    public int getSkillMasterId() {
        return skillMasterId;
    }

    public void setSkillMasterId(int skillMasterId) {
        this.skillMasterId = skillMasterId;
    }

    @Basic
    @Column(name = "SkillLevel", nullable = true)
    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id &&
                skillMasterId == skill.skillMasterId &&
                Objects.equals(skillLevel, skill.skillLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillMasterId, skillLevel);
    }

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "SkillMasterID", referencedColumnName = "ID", nullable = false)
    public Skillmaster getSkillmasterBySkillMasterId() {
        return skillmasterBySkillMasterId;
    }

    public void setSkillmasterBySkillMasterId(Skillmaster skillmasterBySkillMasterId) {
        this.skillmasterBySkillMasterId = skillmasterBySkillMasterId;
    }

    @OneToMany(mappedBy = "skillBySkillId")
    @JsonManagedReference(value="Skillincvs")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Skillincv> getSkillincvsById() {
        return skillincvsById;
    }

    public void setSkillincvsById(List<Skillincv> skillincvsById) {
        this.skillincvsById = skillincvsById;
    }

    @OneToMany(mappedBy = "skillBySkillId")
    @JsonManagedReference(value="Skillneedforjobs")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Skillneedforjob> getSkillneedforjobsById() {
        return skillneedforjobsById;
    }

    public void setSkillneedforjobsById(List<Skillneedforjob> skillneedforjobsById) {
        this.skillneedforjobsById = skillneedforjobsById;
    }
}

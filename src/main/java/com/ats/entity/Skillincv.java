package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Skillincv {
    private int id;
    private int cvid;
    private int skillId;
    private Cv cvByCvid;
    private Skill skillBySkillId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CVID", nullable = false , insertable = false , updatable = false)
    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    @Basic
    @Column(name = "SkillID", nullable = false , insertable = false , updatable = false)
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skillincv skillincv = (Skillincv) o;
        return id == skillincv.id &&
                cvid == skillincv.cvid &&
                skillId == skillincv.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvid, skillId);
    }

    @ManyToOne
    @JsonBackReference(value="CVID")
    @JoinColumn(name = "CVID", referencedColumnName = "ID", nullable = false)
    public Cv getCvByCvid() {
        return cvByCvid;
    }

    public void setCvByCvid(Cv cvByCvid) {
        this.cvByCvid = cvByCvid;
    }

    @ManyToOne
    @JsonBackReference(value="SkillID")
    @JoinColumn(name = "SkillID", referencedColumnName = "ID", nullable = false)
    public Skill getSkillBySkillId() {
        return skillBySkillId;
    }

    public void setSkillBySkillId(Skill skillBySkillId) {
        this.skillBySkillId = skillBySkillId;
    }
}

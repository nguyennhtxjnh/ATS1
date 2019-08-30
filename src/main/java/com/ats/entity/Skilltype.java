package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Skilltype {
    private int id;
    private String typeName;
    private List<Skillmaster> skillmastersById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TypeName", nullable = true, length = 50)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skilltype skilltype = (Skilltype) o;
        return id == skilltype.id &&
                Objects.equals(typeName, skilltype.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }


    @OneToMany(mappedBy = "skilltypeBySkillTypeId")
    @JsonBackReference
    public List<Skillmaster> getSkillmastersById() {
        return skillmastersById;
    }

    public void setSkillmastersById(List<Skillmaster> skillmastersById) {
        this.skillmastersById = skillmastersById;
    }
}

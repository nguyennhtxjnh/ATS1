package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Feedbacktype {
    private int id;
    private String description;
    private List<Feedback> feedbacksById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedbacktype that = (Feedbacktype) o;
        return id == that.id &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @OneToMany(mappedBy = "feedbacktypeByFeedBackTypeId")
    @JsonBackReference
    public List<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(List<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }
}

package com.ats.dto;


import java.io.Serializable;

public class SocialactivitiesDTO implements Serializable {
    private int id;
    private int cvid;
    private String name;
    private String description;

    public SocialactivitiesDTO() {
    }

    public SocialactivitiesDTO(int id, int cvid, String name, String description) {
        this.id = id;
        this.cvid = cvid;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

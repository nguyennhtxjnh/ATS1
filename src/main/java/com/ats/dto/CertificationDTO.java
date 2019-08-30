package com.ats.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CertificationDTO implements Serializable {
    private int id;
    private int cvid;
    private String certificationName;
}

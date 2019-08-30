package com.ats.service;

import com.ats.entity.Certification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificationService {
     List<Certification> getListCertificationByCVID(int id);

     void createANewCertification(Certification newCertification);
}

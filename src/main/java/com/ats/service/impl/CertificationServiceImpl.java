package com.ats.service.impl;

import com.ats.entity.Certification;
import com.ats.repository.CertificationRepository;
import com.ats.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationServiceImpl implements CertificationService{
    @Autowired
    private CertificationRepository certificationRepository;

    @Override
    public List<Certification> getListCertificationByCVID(int id) {
        return certificationRepository.findListCertificationByCVID(id);
    }

    @Override
    public void createANewCertification(Certification newCertification) {
        certificationRepository.save(newCertification);
    }
}

package com.ats.service.impl;

import com.ats.entity.Education;
import com.ats.repository.EducationRepository;
import com.ats.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationRepository educationRepository;

    @Override
    public List<Education> getListEduByCVId(int id) {
        return educationRepository.findAllEduByCVID(id);
    }

    @Override
    public Education createANewEducation(Education newEducation) {
        try {
            educationRepository.save(newEducation);
            return newEducation;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Education editAEducation(Education editedEducation, int id) {
        Education edu = educationRepository.getOne(id);
        if (edu != null){
            // get set bi loi nha
        }
        return null;
    }

    @Override
    public boolean deleteAEducation(int id) {
        Education edu = educationRepository.getOne(id);
        if (edu != null){
            educationRepository.delete(edu);
            return true;
        }
        return false;
    }
}

package com.ats.service.impl;

import com.ats.entity.Workexperience;
import com.ats.repository.WorkexperienceRepository;
import com.ats.service.WorkexperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkexperienceServiceImpl implements WorkexperienceService {
    @Autowired
    private WorkexperienceRepository workexperienceRepository;


    @Override
    public List<Workexperience> getAll() {
        try {
             return workexperienceRepository.findAll();
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Workexperience> getByCVID(int id) {
        try {
           return workexperienceRepository.findByCVID(id);
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<Workexperience> editByID(Workexperience editedWorkexperience, int id) {
        Workexperience work = workexperienceRepository.getOne(id);
        if (work != null){
            work.setCompanyName(editedWorkexperience.getCompanyName());
            work.setDescription(editedWorkexperience.getDescription());
            work.setVacancyName(editedWorkexperience.getVacancyName());
            work.setStartTime(editedWorkexperience.getStartTime());
            work.setEndTime(editedWorkexperience.getEndTime());
            workexperienceRepository.save(work);
            return ResponseEntity.ok().body(work);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public ResponseEntity<Workexperience> deleteByID(Workexperience deletedWorkexperience, int id) {
        Workexperience work = workexperienceRepository.getOne(id);
        if (work != null){
            workexperienceRepository.delete(work);
            return ResponseEntity.ok().body(work);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public boolean createANewWorkExperience(Workexperience newWorkexperience) {
        try {
            workexperienceRepository.save(newWorkexperience);
            return true;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return false;
    }
}

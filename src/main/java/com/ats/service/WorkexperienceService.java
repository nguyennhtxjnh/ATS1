package com.ats.service;

import com.ats.entity.Workexperience;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkexperienceService {
     List<Workexperience> getAll();

     List<Workexperience> getByCVID(int id);

     ResponseEntity<Workexperience> editByID(Workexperience editedWorkexperience, int id);

     ResponseEntity<Workexperience> deleteByID(Workexperience deletedWorkexperience, int id);

     boolean createANewWorkExperience(Workexperience newWorkexperience);
}

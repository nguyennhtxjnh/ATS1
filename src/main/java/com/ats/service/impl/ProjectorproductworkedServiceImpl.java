package com.ats.service.impl;

import com.ats.entity.Projectorproductworked;
import com.ats.repository.ProjectorproductworkedRepository;
import com.ats.service.ProjectorproductworkedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectorproductworkedServiceImpl implements ProjectorproductworkedService {
    @Autowired
    private ProjectorproductworkedRepository projectorproductworkedRepository;

    @Override
    public List<Projectorproductworked> getListProjectByCVID(int id) {
        List<Projectorproductworked> list = projectorproductworkedRepository.findByCVID(id);
        return list;
    }

    @Override
    public ResponseEntity<Projectorproductworked> createANewProjectorProduct(Projectorproductworked newpoProjectorproductworked) {
        try {
            projectorproductworkedRepository.save(newpoProjectorproductworked);
            return ResponseEntity.ok().body(newpoProjectorproductworked);
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public ResponseEntity<Projectorproductworked> editAProjectOrProduct(Projectorproductworked editedProjectorproductworked, int id) {
        // đang suy nghĩ
        return null;
    }
}

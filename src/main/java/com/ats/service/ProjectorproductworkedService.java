package com.ats.service;

import com.ats.entity.Projectorproductworked;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectorproductworkedService {
    List<Projectorproductworked> getListProjectByCVID(int id);

    ResponseEntity<Projectorproductworked> createANewProjectorProduct(Projectorproductworked newpoProjectorproductworked);

    ResponseEntity<Projectorproductworked> editAProjectOrProduct(Projectorproductworked editedProjectorproductworked, int id);
}

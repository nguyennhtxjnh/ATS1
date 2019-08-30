package com.ats.ws;

import com.ats.entity.Projectorproductworked;
import com.ats.service.ProjectorproductworkedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectwored")
public class ProjectorproductworkedWS {
    @Autowired
    private ProjectorproductworkedService projectorproductworkedService;


    // Get by CVID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<List<Projectorproductworked>> getByCVID(@PathVariable int id){
        return ResponseEntity.ok().body(projectorproductworkedService.getListProjectByCVID(id));
    }

    // Create New ProjectorProduct
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<Projectorproductworked> createANewProjectorproductworked
    (@RequestBody Projectorproductworked newproProjectorproductworked){
        return projectorproductworkedService.createANewProjectorProduct(newproProjectorproductworked);
    }

    // Edit One ProjectorProduct
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public ResponseEntity<Projectorproductworked> editAProjectOrProduct
    (@RequestBody Projectorproductworked editedProjectorproductworked, @PathVariable int id){
        return null;
    }
}

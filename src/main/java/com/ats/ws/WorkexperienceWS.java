package com.ats.ws;

import com.ats.entity.Workexperience;
import com.ats.service.WorkexperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worlexperience")
public class WorkexperienceWS {
    @Autowired
    private WorkexperienceService workexperienceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin (origins = "")
    public ResponseEntity<List<Workexperience>> getAll(){
        List<Workexperience> list = workexperienceService.getAll();
        if (list != null)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.badRequest().body(null);
    }

    // Get list of workexperience by CVID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<List<Workexperience>> getByCVID(@PathVariable int id){
        List<Workexperience> list = workexperienceService.getByCVID(id);
        if (list != null)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.badRequest().body(null);
    }

    // Edit One WorkExeperience by CVID
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public ResponseEntity<Workexperience> editByID(@RequestBody Workexperience editedWorkexperience, @PathVariable int id){
        return workexperienceService.editByID(editedWorkexperience, id);
    }

    // Delete One WorkExperience by ID
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<Workexperience> deleteByID(@RequestBody Workexperience deletedWorkexperience, @PathVariable int id){
        return workexperienceService.deleteByID(deletedWorkexperience, id);
    }

    // Create One WorkExperience
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<Workexperience> createANewWorkexperience(@RequestBody Workexperience newWorkexperience){
        Boolean check = workexperienceService.createANewWorkExperience(newWorkexperience);
        if (check)
            return ResponseEntity.ok().body(newWorkexperience);
        return ResponseEntity.badRequest().body(null);
    }
}

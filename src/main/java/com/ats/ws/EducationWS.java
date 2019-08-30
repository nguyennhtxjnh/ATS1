package com.ats.ws;

import com.ats.entity.Education;
import com.ats.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
public class EducationWS {
    @Autowired
    private EducationService educationService;

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @CrossOrigin(origins = "")
//    public ResponseEntity<List<Education>> getALLByCVID(@PathVariable int id){
//        return educationService.getListEduByCVId(id);
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<Education> createANewEducation(@RequestBody Education newEducation){
        return ResponseEntity.ok().body(educationService.createANewEducation(newEducation));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public ResponseEntity<Education> editAEducation(@RequestBody Education editedEducation, @PathVariable int id){

        return null;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public boolean deleteAEducation(@PathVariable int id){
        return educationService.deleteAEducation(id);
    }
}

package com.ats.ws;

import com.ats.entity.Socialactivities;
import com.ats.service.SocialactivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SocialactivityWS {
    @Autowired
    private SocialactivityService socialactivityService;

    // find list socialactivity of one CV
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<List<Socialactivities>> findByCVID (@PathVariable int id){
        return  ResponseEntity.ok().body(socialactivityService.findListSocialactivityByCVID(id));
    }

    // edit one social activity
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public boolean editASocialactivity(@RequestBody Socialactivities editedSocialactivity, @PathVariable int id){
        if (socialactivityService.editASocialactivity(editedSocialactivity, id))
            return true;
        return false;
    }

    // Create a new SocialActivity
    @RequestMapping(value = "", method = RequestMethod.POST)
    @CrossOrigin (origins = "")
    public ResponseEntity<Socialactivities> creaete(@RequestBody Socialactivities newSocialactivity){
        if (socialactivityService.createANewSocialactivity(newSocialactivity) != null)
            return ResponseEntity.ok().body(newSocialactivity);
        return ResponseEntity.badRequest().body(null);
    }


    // find one socialactivity by ID
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @CrossOrigin(origins = "")
//    public ResponseEntity findOneByID(@PathVariable int id){
//        Socialactivities soc = socialactivityService.findOneByID(id);
//        if(soc != null)
//            return ResponseEntity.ok().body(soc);
//        return ResponseEntity.badRequest().body(null);
//    }

    // Delete one socialactivity by ID


}

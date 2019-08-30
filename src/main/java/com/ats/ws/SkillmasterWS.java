package com.ats.ws;

import com.ats.dto.SkillMasterDTO;
import com.ats.entity.Skillmaster;
import com.ats.service.SkillmasterService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skillmaster")
public class SkillmasterWS {
    @Autowired
    private SkillmasterService skillmasterService;

    private static final Logger LOGGER = LogManager.getLogger(SkillmasterWS.class);

    // LIST RA HET BO VO DROP DOWN CHO NGUOI NHAP CHON
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<SkillMasterDTO>> getAll(){
        return ResponseEntity.ok().body(skillmasterService.listAll());
    }

    // get for mobile
    @GetMapping("/mobile")
    @CrossOrigin(origins = "*")
    public List<SkillMasterDTO> getAllMobile(){
        return skillmasterService.listAll();
    }


    // CREATE A NEW SKILL DO MAY BAN
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public boolean createNewSkillmaster(@RequestBody SkillMasterDTO newSkillmaster){
        if (skillmasterService.createANewSkillMaster(newSkillmaster))
            return true;
        return false;
    }

    // EDIT MOT SKILL IN ONE CV
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @CrossOrigin(origins = "*")
    public boolean editASkillmaster(@RequestBody SkillMasterDTO editedSkillmaster){
           if (skillmasterService.editASkillmaster(editedSkillmaster))
               return true;
           return false;
    }

    // GET All LANGUAGE SKILL
    @RequestMapping(value = "/language/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Skillmaster>> findAllLanguageskill(@PathVariable int id){
        return ResponseEntity.ok().body(skillmasterService.findAllLanguageskill(id));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAllSkill")
    public RestResponse getAllSkill(@PageableDefault Pageable pageable,
                                  @RequestParam("search") String search,
                                  @RequestParam("status") String status) {
        LOGGER.info("Begin getAllSkill in JobWS");
        pageable = new PageRequest(0, Integer.MAX_VALUE);
        Page<Skillmaster> liskSkill = null;
        try {
            liskSkill = skillmasterService.getAllSkillMaster(pageable, search, status);
            return new RestResponse(true, "Get getAllSkill Successful", liskSkill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllSkill in JobWS");
        return new RestResponse(false, "No Job is Available : ", null);
    }
}

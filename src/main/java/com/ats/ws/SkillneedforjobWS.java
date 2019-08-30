package com.ats.ws;


import com.ats.dto.SkillNeedForJobDTO;
import com.ats.service.SkillNeedForJobService;
import com.ats.service.SkillService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/skillneedforjob")
public class SkillneedforjobWS {
    @Autowired
    SkillService skillService;
    @Autowired
    SkillNeedForJobService skillNeedForJobService;

    private static final Logger LOGGER = LogManager.getLogger(SkillneedforjobWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addNewSkill" )
    public RestResponse createSkill(@RequestBody SkillNeedForJobDTO dto) {
        LOGGER.info("Begin createSkill in SkillneedforjobWS with Job id : {}" + dto.getJobId());
        System.out.println(dto.toString());
        List<Integer> listSkillId = new ArrayList<>();
        try {
            for (int i = 0; i < dto.getListSkill().size(); i++) {
                listSkillId.add(skillService.addNewSkill(dto.getListSkill().get(i)));
            }
            LOGGER.info("End createSkill in SkillneedofrjobWS with Job id : {}" + dto.getJobId());
            boolean finish = skillNeedForJobService.addSkillForJob(listSkillId, dto.getJobId());
            if (finish) {
                return new RestResponse(true, "Create New Skill Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Skill ", null);
    }
}

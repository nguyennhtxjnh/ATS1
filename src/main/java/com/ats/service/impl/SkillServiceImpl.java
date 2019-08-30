package com.ats.service.impl;

import com.ats.dto.SkillDTO;
import com.ats.entity.Skill;
import com.ats.entity.Skillmaster;
import com.ats.entity.Skillneedforjob;
import com.ats.repository.SkillRepository;
import com.ats.service.SkillService;
import com.ats.service.SkillmasterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillmasterService skillmasterService;

    private static final Logger LOGGER = LogManager.getLogger(SkillServiceImpl.class);

    @Override
    public int addNewSkill(SkillDTO skilldto) {
        LOGGER.info("Begin addNewSkill in Skill Service with skillmaster id {}", skilldto.getSkillMasterId());
        int skillResult = -1;
        Skill skill = new Skill();
        try {
            skillResult = checkSkillBySkillLevel(skilldto);
            ModelMapper mapper = new ModelMapper();
            skill = mapper.map(skilldto, Skill.class);
            if (skillResult == -1) {
                Skillmaster skillmaster = new Skillmaster();
                skillmaster.setId(skill.getSkillMasterId());
                skill.setSkillmasterBySkillMasterId(skillmaster);
                skill = skillRepository.save(skill);
                skillResult = skill.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End addNewSkill in Skill Service with skillmaster id {}", skill.getSkillMasterId());
        return skillResult;
    }

    @Override
    public int checkSkillBySkillLevel(SkillDTO skill) {
        LOGGER.info("Begin checkSkillBySkillLevel in Skill Service with skill master with skill level {}",
                skill.getSkillMasterId() + "-" + skill.getSkillLevel());
        try {
            Skill skillResult = skillRepository.findSkillbySkillLevel(skill.getSkillLevel(), skill.getSkillMasterId());
            LOGGER.info("End checkSkillBySkillLevel in Skill Service with skill master with skill level {}",
                    skill.getSkillMasterId() + "-" + skill.getSkillLevel());
            if (skillResult != null) {
                return skillResult.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<String> getSkillName(List<Skillneedforjob> skillneedforjob) {
        LOGGER.info("Begin getSkillName in Skill Service with List size : ",
                skillneedforjob.size());
        List<Integer> listSkillMasterId = new ArrayList<>();
        List<String> listSkillName = new ArrayList<>();
        try {
            for (int i = 0; i < skillneedforjob.size(); i++) {
                Skill skill = skillRepository.findOne(skillneedforjob.get(i).getSkillId());
                listSkillMasterId.add(skill.getSkillMasterId());
            }

            listSkillName  = skillmasterService.getSkillNameById(listSkillMasterId);
            LOGGER.info("End getSkillName in Skill Service with List size : ",
                    skillneedforjob.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSkillName;
    }
}

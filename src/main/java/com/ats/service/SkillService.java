package com.ats.service;

import com.ats.dto.SkillDTO;
import com.ats.entity.Skillneedforjob;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillService {
    int addNewSkill(SkillDTO skill);

    int checkSkillBySkillLevel(SkillDTO skill);

    List<String> getSkillName(List<Skillneedforjob> Skillneedforjob);
}

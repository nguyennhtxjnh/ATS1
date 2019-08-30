package com.ats.service;

import com.ats.dto.SkillMasterDTO;
import com.ats.entity.Skillmaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillmasterService {
     boolean createANewSkillMaster(SkillMasterDTO skillmaster);

     List<SkillMasterDTO> listAll();

     boolean editASkillmaster(SkillMasterDTO editedSkillmaster);

     List<Skillmaster> findAllLanguageskill(int id);

     List<String> getSkillNameById(List<Integer> integerList);

     Page<Skillmaster> getAllSkillMaster(Pageable pageable,String search, String type);
}

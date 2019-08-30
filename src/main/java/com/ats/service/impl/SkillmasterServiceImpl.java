package com.ats.service.impl;

import com.ats.dto.SkillMasterDTO;
import com.ats.entity.Skillmaster;
import com.ats.entity.Skilltype;
import com.ats.repository.SkillmasterRepository;
import com.ats.service.SkillmasterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SkillmasterServiceImpl implements SkillmasterService {
    @Autowired
    private SkillmasterRepository skillmasterRepository;

    private static final Logger LOGGER = LogManager.getLogger(SkillmasterServiceImpl.class);

    @Override
    public boolean createANewSkillMaster(SkillMasterDTO skillmasterDTO) {
        try {
            Skillmaster newSkillMaster;
            ModelMapper mapper = new ModelMapper();
            newSkillMaster = mapper.map(skillmasterDTO,Skillmaster.class);
            Skilltype skilltype = new Skilltype();
            skilltype.setId(newSkillMaster.getSkillTypeId());
            newSkillMaster.setSkilltypeBySkillTypeId(skilltype);
            skillmasterRepository.save(newSkillMaster);
            return true;
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<SkillMasterDTO> listAll() {
        ModelMapper mapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<SkillMasterDTO>>() {
        }.getType();
        List<SkillMasterDTO> listofDTO;
        List<Skillmaster> listSkillMaster = skillmasterRepository.findAll();
        listofDTO = mapper.map(listSkillMaster, targetListType);
        return listofDTO;
    }

    @Override
    public boolean editASkillmaster(SkillMasterDTO editedSkillmaster) {
        try {
            Skillmaster skillmaster = skillmasterRepository.findOne(editedSkillmaster.getId());

            if (skillmaster != null) {
                skillmaster.setSkillName(editedSkillmaster.getSkillName());
                Skilltype skilltype = new Skilltype();
                skilltype.setId(editedSkillmaster.getSkillTypeId());
                skillmaster.setSkilltypeBySkillTypeId(skilltype);

                skillmasterRepository.save(skillmaster);
                return true;
            }
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Skillmaster> findAllLanguageskill(int id) {
        try {
            return skillmasterRepository.findAllLanguageSkill(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<String> getSkillNameById(List<Integer> integerList) {
        LOGGER.info("Begin getSkillNameById in SkillMaster Service with List size : ", integerList.size());
        List<String> listSkillName = new ArrayList<>();

        try {
            for (int i = 0; i < integerList.size(); i++) {
                Skillmaster skillmaster = skillmasterRepository.findOne(integerList.get(i));
                listSkillName.add(skillmaster.getSkillName());
            }
            Set<String> set = new HashSet<>(listSkillName);
            listSkillName.clear();
            listSkillName.addAll(set);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getSkillNameById in SkillMaster Service with List size : ", integerList.size());
        return listSkillName;
    }

    @Override
    public Page<Skillmaster> getAllSkillMaster(Pageable pageable, String search, String type) {
        LOGGER.info("Begin getAllSkillMaster in SM Service");
        Page<Skillmaster> listofJob = null;
        try {
            LOGGER.info("Begin getAllSkillMaster in SM Repository ");
            System.out.println(search);
            listofJob = skillmasterRepository.getAll(pageable, search, type);
            System.out.println(listofJob.getContent().size());
            LOGGER.info("End getAllSkillMaster in SM Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllSkillMaster in SM Service");
        return listofJob;
    }
}

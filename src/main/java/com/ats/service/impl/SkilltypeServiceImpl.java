package com.ats.service.impl;

import com.ats.entity.Skilltype;
import com.ats.repository.SkilltypeRepository;
import com.ats.service.SkilltypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkilltypeServiceImpl implements SkilltypeService  {
    @Autowired
    private SkilltypeRepository skilltypeRepository;


    @Override
    public boolean createANewSkilltype(Skilltype newSkilltype) {
        try {
            skilltypeRepository.save(newSkilltype);
            return true;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean editASkilltype(Skilltype editedSkilltype, int id) {
        try {
            Skilltype skilltype = skilltypeRepository.getOne(id);
            if (skilltype != null){
                skilltype.setTypeName(editedSkilltype.getTypeName());
                skilltypeRepository.save(skilltype);
                return true;
            }
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return false;
    }
}

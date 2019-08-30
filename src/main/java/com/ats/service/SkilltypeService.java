package com.ats.service;

import com.ats.entity.Skilltype;
import org.springframework.stereotype.Service;

@Service
public interface SkilltypeService {
     boolean createANewSkilltype(Skilltype newSkilltype);

     boolean editASkilltype(Skilltype editedSkilltype, int id);
}

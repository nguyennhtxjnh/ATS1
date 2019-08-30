package com.ats.service.impl;

import com.ats.entity.Socialactivities;
import com.ats.repository.SocialactivityRepository;
import com.ats.service.SocialactivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialactivityServiceImpl implements SocialactivityService {
    @Autowired
    private SocialactivityRepository socialactivityRepository;

    @Override
    public List<Socialactivities> findListSocialactivityByCVID(int id) {
        return socialactivityRepository.findAllSocialactivityByCVID(id);
    }

    @Override
    public boolean editASocialactivity(Socialactivities editedSocialactivity ,int id) {
        Socialactivities  soc = socialactivityRepository.getOne(id);
        if (soc != null){
            soc.setName(editedSocialactivity.getName());
            soc.setDescription(editedSocialactivity.getDescription());
            socialactivityRepository.save(soc);
            return true;
        }
        return false;
    }

    @Override
    public Socialactivities createANewSocialactivity(Socialactivities newSocialactivity) {
        try {
            socialactivityRepository.save(newSocialactivity);
            return newSocialactivity;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Socialactivities findOneByID(int id) {
        return socialactivityRepository.getOne(id);
    }

    @Override
    public void deleteOneByID(int id) {
        socialactivityRepository.delete(id);
    }
}


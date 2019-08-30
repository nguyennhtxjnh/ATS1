package com.ats.service;

import com.ats.entity.Socialactivities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocialactivityService {
     List<Socialactivities> findListSocialactivityByCVID(int id);

     boolean editASocialactivity(Socialactivities editedSocialactivity,int id);

     Socialactivities createANewSocialactivity(Socialactivities newSocialactivity);

     Socialactivities findOneByID(int id);

     void deleteOneByID(int id);
}

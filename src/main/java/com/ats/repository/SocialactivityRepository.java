package com.ats.repository;

import com.ats.entity.Socialactivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialactivityRepository extends JpaRepository<Socialactivities, Integer> {
    @Query("Select s from Socialactivities s where s.cvid = :id")
    List<Socialactivities> findAllSocialactivityByCVID (@Param("id") int id);
}

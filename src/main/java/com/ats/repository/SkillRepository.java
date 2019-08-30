package com.ats.repository;


import com.ats.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Query("Select s from Skill s where s.skillLevel = :skillLevel and s.skillMasterId = :skillMaster")
    Skill findSkillbySkillLevel(@Param("skillLevel") int skillLevel, @Param("skillMaster") int skillMaster);
}

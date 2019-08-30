package com.ats.repository;

import com.ats.entity.Skillincv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillincvRepository extends JpaRepository<Skillincv, Integer> {
    List<Skillincv> findSkillincvsByCvid(int cvid);
}

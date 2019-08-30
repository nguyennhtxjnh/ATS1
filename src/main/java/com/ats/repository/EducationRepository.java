package com.ats.repository;

import com.ats.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education,Integer> {
    @Query("Select e from Education e where e.cvid = :id")
    List<Education> findAllEduByCVID (@Param("id") int id);
}

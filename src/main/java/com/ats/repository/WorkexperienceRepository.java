package com.ats.repository;

import com.ats.entity.Workexperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkexperienceRepository extends JpaRepository<Workexperience, Integer> {
    @Query("Select w from Workexperience w where w.cvid = :id")
    List<Workexperience> findByCVID(@Param("id") int id);
}

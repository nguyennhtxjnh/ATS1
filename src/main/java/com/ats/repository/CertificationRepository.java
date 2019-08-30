package com.ats.repository;

import com.ats.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer> {
    @Query("Select c from Certification c where c.cvid = :id")
    List<Certification> findListCertificationByCVID(@Param("id") int id);
}

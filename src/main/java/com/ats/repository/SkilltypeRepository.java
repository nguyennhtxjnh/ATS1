package com.ats.repository;

import com.ats.entity.Skilltype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkilltypeRepository extends JpaRepository<Skilltype, Integer> {
}

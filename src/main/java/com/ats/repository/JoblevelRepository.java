package com.ats.repository;

import com.ats.entity.Joblevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoblevelRepository extends JpaRepository<Joblevel, Integer>
{
}

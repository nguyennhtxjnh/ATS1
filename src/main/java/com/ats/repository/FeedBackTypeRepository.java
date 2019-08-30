package com.ats.repository;

import com.ats.entity.Feedbacktype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackTypeRepository extends JpaRepository<Feedbacktype,Integer> {
}

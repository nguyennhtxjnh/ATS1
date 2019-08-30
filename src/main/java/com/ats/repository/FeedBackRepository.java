package com.ats.repository;

import com.ats.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Integer> {
    @Query("select f from Feedback f where " +
            "f.jobByJobId.title LIKE CONCAT('%',LOWER(:search),'%') OR " +
            "f.jobByJobId.companyByCompanyId.nameCompany LIKE CONCAT('%',LOWER(:search),'%') order by f.createdDate desc")
    Page<Feedback> findAllByCreatedDate(Pageable pageable, @Param("search") String search);

    @Query("select f from Feedback f where f.userId = :userId")
    List<Feedback> checkFeedBack(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Feedback f SET f.isReply = 1 WHERE f.userId = :userId and f.jobId = :jobId")
    int checkIsReply(@Param("userId") int userId, @Param("jobId") int jobId);
}

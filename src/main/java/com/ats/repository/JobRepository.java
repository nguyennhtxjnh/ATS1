package com.ats.repository;

import com.ats.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("SELECT distinct j FROM Job j " +
            "INNER JOIN j.skillneedforjobsById s " +
            "INNER JOIN s.skillBySkillId a " +
            "INNER JOIN a.skillmasterBySkillMasterId m " +
            "INNER JOIN j.cityByCityId c " +
            "INNER JOIN j.industryByIndustryId d " +
            "WHERE j.status = :status and " +
            "c.fullName LIKE CONCAT('%',LOWER(:city),'%') and " +
            "d.name LIKE CONCAT('%',LOWER(:industry),'%') and " +
            "j.endDateForApply > :now and " +
            "(j.title LIKE CONCAT('%',LOWER(:search),'%')" +
            "OR m.skillName LIKE CONCAT('%',LOWER(:search),'%')" +
            "OR j.companyByCompanyId.nameCompany LIKE CONCAT('%',LOWER(:search),'%'))")
    Page<Job> searchJob(@Param("search") String search, Pageable pageable,
                        @Param("status") String status, @Param("now") Date endDateForApply,
                        @Param("city") String city, @Param("industry") String industry);

    @Query("Select b from Job b " +
            "where b.status = :status and " +
            "b.endDateForApply > :now " +
            "order by b.createdDate desc")
    List<Job> getTop8Mobile(@Param("status") String status, @Param("now") Date endDateForApply);

    @Query("Select b from Job b " +
            "where b.status = :status and " +
            "b.endDateForApply > :now " +
            "order by b.createdDate desc")
    Page<Job> getTop8(Pageable pageable, @Param("status") String status, @Param("now") Date endDateForApply);

    @Query("Select b from Job b " +
            "where b.status LIKE CONCAT('%',LOWER(:status),'%') and " +
            "b.userId = :employerId ")
    Page<Job> findAllByEmployerId(Pageable pageable,
                                  @Param("status") String status,
                                  @Param("employerId") int employerId);


    @Query("Select b from Job b where b.companyId = :companyId and b.id <> :jobId")
    List<Job> getJobByCompanyID(@Param("companyId") int companyId, @Param("jobId") int jobId);

    @Query("Select b from Job b where b.companyId = :companyId and b.status = :status")
    Page<Job> getJobByCompanyId(Pageable pageable,
                                @Param("companyId") int companyId,
                                @Param("status") String status);

    @Query("Select b from Job b where b.companyId = :companyId " +
            "and b.status = :status " +
            "and b.endDateForApply > :now")
    Page<Job> getInvalidJobByCompanyId(Pageable pageable,
                                       @Param("companyId") int companyId,
                                       @Param("status") String status,
                                       @Param("now") Timestamp now);

    @Transactional
    @Modifying
    @Query("UPDATE Job b SET b.status = :newStatus WHERE b.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);

    @Query("SELECT distinct j FROM Job j " +
            "INNER JOIN j.cityByCityId c " +
            "INNER JOIN j.industryByIndustryId d " +
            "WHERE j.status = :status and " +
            "j.yearExperience <= :yE and " +
            "j.endDateForApply > :now and " +
            "c.fullName LIKE CONCAT('%',LOWER(:cityId),'%') and " +
            "d.name LIKE CONCAT('%',LOWER(:industryId),'%')")
    Page<Job> suggestJob(@Param("yE") int yearExperience,
                         @Param("industryId") String industryId,
                         @Param("cityId") String city,
                         @Param("status") String status,
                         @Param("now") Date now,
                         Pageable pageable);

    Optional<Job> findById(int id);

    List<Job> findByUserId(int id);

    @Query("Select j from Job  j where j.userId = :userid and j.endDateForApply >= :now and j.status = :status")
    List<Job> getJobValid(@Param("userid") int userid, @Param("now") Timestamp now, @Param("status") String status);

    @Query("Select j from Job  j where j.userId = :userid and j.endDateForApply <= :now")
    List<Job> getJobInValid(@Param("userid") int userid, @Param("now") Timestamp now);

    @Query("Select j from Job j " +
            "INNER JOIN j.companyByCompanyId c " +
            "where j.status LIKE CONCAT('%',LOWER(:status),'%') and " +
            "(j.title LIKE CONCAT('%',LOWER(:search),'%') OR " +
            "c.nameCompany LIKE CONCAT('%',LOWER(:search),'%'))")
    Page<Job> getAll(Pageable pageable, @Param("search") String search, @Param("status") String status);

    List<Job> findJobsByStatusAndUserId(String status, int EmployerId);

    @Query("Select j from Job j where j.userId = :employerId")
    List<Job> getJobsByEmployerId(@Param("employerId") int employerId);

    @Query("Select j from Job j where j.companyId = :companyId")
    List<Job> getNumberOfJobByCompanyByCompanyId(@Param("companyId") int companyId);
}

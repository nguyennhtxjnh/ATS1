package com.ats.repository;

import com.ats.entity.Jobseekerlikejob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobseekerlikejobRespository extends JpaRepository<Jobseekerlikejob, Integer> {
    List<Jobseekerlikejob> findByUserIdAndJobId(int userid, int jobid);

    List<Jobseekerlikejob> findJobseekerlikejobsByUserId(int EmployerId);
}

package com.ats.service;

import com.ats.dto.JobDTO;
import com.ats.util.RestResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobseekerlikejobService {
    boolean check(int JobSeekerId, int Jobid);

    RestResponse create(int JobSeekerId, int JobId);

    RestResponse listJob(int JobSeekerId);

    List<JobDTO> listJobMobile(int JobSeekerId);

    RestResponse unCheck(int JobSeekerId, int JobId);
}

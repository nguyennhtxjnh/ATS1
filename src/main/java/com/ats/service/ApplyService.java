package com.ats.service;

import com.ats.dto.JobDTO;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplyService {
    RestResponse create(int CvId, int JobID);

    RestResponse confirm(int JobId, int CvId);

    RestResponse deny(int JobId, int CvId);

    boolean check(int JobSeekerID, int JobID);

    RestResponse listJob(int JobSeekerId);

    List<JobDTO> listJobMobile(int JobSeekerId);

    RestResponse listCv(int JobId, Pageable pageable);

    RestResponse getAllApply(int userId);

    int getAllApplyMobile(int userId);

    RestResponse checkStatusApply(int CvId, int JobId);
}

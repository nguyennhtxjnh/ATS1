package com.ats.service;

import com.ats.entity.Skillneedforjob;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillNeedForJobService {
    boolean addSkillForJob(List<Integer> listSkillId , int jobId);

    int checkSkillNeedForJob(Skillneedforjob entity);

    boolean updateSkillForJob(List<Integer> listSkillId , int jobId);
}

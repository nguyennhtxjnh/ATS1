package com.ats.service;

public interface CountjobService {
    void countWhenEmployerGetDetailOfJob(int JobID, int JodSeekerID);

    int countAccessTimes(int EmployerId);
}


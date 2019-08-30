package com.ats.service;

public interface CountcvService {
    boolean countWhenEmployerGetDetailOfCV(int CVID, int EmployerID);

    int countAccessTimes(int JobSeekerId);
}

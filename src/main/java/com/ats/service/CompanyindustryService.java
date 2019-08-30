package com.ats.service;

import org.springframework.stereotype.Service;

@Service
public interface CompanyindustryService {
    void create(Integer companyId, Integer industryId);
}

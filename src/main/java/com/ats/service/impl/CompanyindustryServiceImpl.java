package com.ats.service.impl;

import com.ats.entity.Company;
import com.ats.entity.Companyindustry;
import com.ats.entity.Industry;
import com.ats.repository.CompanyRepository;
import com.ats.repository.CompanyindustryRepository;
import com.ats.repository.IndustryRepository;
import com.ats.service.CompanyindustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

@Service
public class CompanyindustryServiceImpl implements CompanyindustryService {
    @Autowired
    private CompanyindustryRepository companyindustryRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public void create(Integer companyId, Integer industryId) {
        Company company = companyRepository.findCompanyById(companyId).orElseThrow(() -> new NotFoundException("Not found Company with Id: " + companyId));
        Industry industry = industryRepository.findIndustryById(industryId).orElseThrow(()-> new NotFoundException("Not found industry have Id: " + industryId));
        Companyindustry companyindustry = companyindustryRepository.findCompanyindustry(companyId, industryId);
        if(companyindustry == null){
            Companyindustry saveCompanyIndustry = new Companyindustry();
            saveCompanyIndustry.setCompanyId(companyId);
            saveCompanyIndustry.setIndustryId(industryId);
            saveCompanyIndustry.setCompanyByCompanyId(company);
            saveCompanyIndustry.setIndustryByIndustryId(industry);
            companyindustryRepository.save(saveCompanyIndustry);
        }
    }
}

package com.ats.ws;

import com.ats.dto.CompanyindustryDTO;
import com.ats.service.CompanyindustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companyindustry")
public class CompanyindustryWS {
    @Autowired
    private CompanyindustryService companyindustryService;

    // Test POST
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test(@RequestBody CompanyindustryDTO newCompanyIndustry){
        companyindustryService.create(newCompanyIndustry.getCompanyId(), newCompanyIndustry.getIndustryId());
    }
}

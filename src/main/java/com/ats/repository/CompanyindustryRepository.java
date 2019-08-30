package com.ats.repository;

import com.ats.entity.Companyindustry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyindustryRepository extends JpaRepository<Companyindustry, Integer> {
    @Query("Select c from Companyindustry c where c.companyId = :companyid and c.industryId = :industryid")
    Companyindustry findCompanyindustry (@Param("companyid") int companyid, @Param("industryid") int industryid);
}

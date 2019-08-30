package com.ats.service;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.dto.CompanyDTO3;
import com.ats.entity.Company;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    Company findComanyByID(int id);

    List<CompanyDTO2> listAll();

    List<CompanyDTO3> listAllAdmin();

    RestResponse create(CompanyDTO newCompany);

    int changeStatus(int id, String newStatus);

    Page<CompanyDTO3> findAllCompanyByStatus(String search, String status, Pageable pageable);

    List<CompanyDTO> getFiveCompany();
}

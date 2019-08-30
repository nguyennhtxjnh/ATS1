package com.ats.service;

import com.ats.dto.EmployercompanyDTO;
import com.ats.dto.EmployercompanyDTO2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmployercompanyService {

    boolean createNewEmployerCompany(EmployercompanyDTO dto);

    boolean createNewEmployerExistedCompany(EmployercompanyDTO dto);

    boolean changeUserStatusAfterApproved(int companyId);

    int findCompanyById(int id);

    EmployercompanyDTO findCompanyByUserId(int userId);

    int changeStatus(EmployercompanyDTO employercompanyDTO);

    Page<EmployercompanyDTO2> getAllEmployerCompanyByUserId(String search ,
                                                            String status ,
                                                            int userId,
                                                            Pageable pageable);
}

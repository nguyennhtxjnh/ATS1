package com.ats.service;

import com.ats.dto.CVDTO;
import com.ats.dto.CVMobileDTO;
import com.ats.entity.Cv;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CVService {
    RestResponse getCVByCVID(int id);

    RestResponse create(CVDTO newCV);

    RestResponse edit(CVDTO editedCv);

    RestResponse delete(int id);

    RestResponse getlistCvByUserId(int id);

    List<CVMobileDTO> getListCvByUserIdMobile(int id);

    RestResponse checkActive(int id);

    RestResponse setMainCv(int id);

    Page<Cv> searchCv(String skillstring, String cityId, String industryId, Pageable pageable);

    Page<Cv> suggest(int JobId, Pageable pageable);

    Page<Cv> listCvConfirmed (int EmployerId, Pageable pageable);

    Page<Cv> listCvDenied (int EmployerId, Pageable pageable);

    boolean check(int JobSeekerId);
}
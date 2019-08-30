package com.ats.service;

import com.ats.dto.ServicePackageDTO;
import com.ats.dto.ServicePackageDTO2;
import com.ats.entity.Servicepackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ServicePackageService {
    int createServicePack(ServicePackageDTO servicePackageDTO);

    int updateServicePack(ServicePackageDTO servicePackageDTO);

    int changeServicePackStatus(int id, String newStatus);

    Page<Servicepackage> getAllServicePack(Pageable pageable, String search, String status);

    ServicePackageDTO2 getServicePackDetail(int id);
}

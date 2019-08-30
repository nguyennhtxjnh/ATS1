package com.ats.service.impl;

import com.ats.entity.Cv;
import com.ats.entity.Users;
import com.ats.entity.Userslikecv;
import com.ats.repository.CVRepository;
import com.ats.repository.UserlikecvRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.UserlikecvService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserlikecvServiceImpl implements UserlikecvService {
    @Autowired
    private UserlikecvRepository userlikecvRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CVRepository cvRepository;

    @Override
    public boolean check(int EmployerId, int Cvid) {
        List<Userslikecv> userslikecv = userlikecvRepository.check(EmployerId, Cvid);
        if (userslikecv.size() == 0)
            return true;
        return false;
    }

    @Override
    public RestResponse create(int EmployerId, int CvId) {
        Users users = usersRepository.findOne(EmployerId);
        Cv cv = cvRepository.findOne(CvId);
        if (users == null || cv == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        Userslikecv userslikecv = new Userslikecv();
        userslikecv.setCvid(CvId);
        userslikecv.setUserId(EmployerId);
        userslikecv.setCvByCvid(cv);
        userslikecv.setUsersByUserId(users);
        userslikecv.setCreatedDate(new Timestamp(new Date().getTime()));
        userlikecvRepository.save(userslikecv);
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public RestResponse listCv(int EmployerId, Pageable pageable) {
        Users user = usersRepository.findOne(EmployerId);
        if (user == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        List<Userslikecv> listUserslikecv = userlikecvRepository.findUserslikecvsByUserId(EmployerId);
        if (listUserslikecv == null)
            return new RestResponse(false, "Không có ứng viên nào ứng tuyển!!!", null);
        List<Cv> listCv = new ArrayList<>();
        for (Userslikecv userslikecv : listUserslikecv) {
            listCv.add(userslikecv.getCvByCvid());
        }
        Page<Cv> pageCv = new PageImpl<>(listCv, pageable, listCv.size());
        return new RestResponse(true, "Thành công!!!", pageCv);

    }

    @Override
    public RestResponse unCheck(int EmployerId, int CvId) {
        Users user = usersRepository.findOne(EmployerId);
        Cv cv = cvRepository.findOne(CvId);
        if (user == null || cv == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        userlikecvRepository.delete(userlikecvRepository.check(EmployerId, CvId));
        return new RestResponse(true, "Thành công!!!", null);
    }
}
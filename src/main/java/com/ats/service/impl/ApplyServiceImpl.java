package com.ats.service.impl;

import com.ats.dto.JobDTO;
import com.ats.entity.Apply;
import com.ats.entity.Cv;
import com.ats.entity.Job;
import com.ats.repository.ApplyRepository;
import com.ats.repository.CVRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.ApplyService;
import com.ats.service.EmailService;
import com.ats.util.RestResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UsersRepository usersRepository;
    //Mapping Object
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RestResponse create(int CvId, int JobID) {
        Job job = jobRepository.findOne(JobID);
        Cv cv = cvRepository.findOne(CvId);
        if (job == null || cv == null)
            return new RestResponse(false, "Lỗi: việc làm này không tồn tại!", null);
        Apply apply = new Apply();
        apply.setJobId(JobID);
        apply.setCvid(CvId);
        apply.setCvByCvid(cv);
        apply.setJobByJobId(job);
        apply.setDayApply(new Timestamp(new Date().getTime()));
        apply.setStatus("1");
        applyRepository.save(apply);
        emailService.sendEmailForJob(job.getUsersByUserId().getEmail(), job.getTitle(),
                cv.getUsersByUserId().getFullName(), "apply");
        return new RestResponse(true, "Bạn đã ứng tuyển vào công việc này thành công!!!", null);
    }

    @Override
    public RestResponse confirm(int JobId, int CvId) {
        Apply apply = applyRepository.findForCheck(CvId, JobId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        apply.setStatus("2");
        applyRepository.save(apply);
        emailService.sendEmailForJob(apply.getCvByCvid().getUsersByUserId().getEmail(), apply.getJobByJobId().getTitle(),
                apply.getCvByCvid().getUsersByUserId().getFullName(), "confirm");
        // goi mail serviec gui mail cho employer
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public RestResponse deny(int JobId, int CvId) {
        Apply apply = applyRepository.findForCheck(CvId, JobId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        apply.setStatus("3");
        applyRepository.save(apply);
        emailService.sendEmailForJob(apply.getCvByCvid().getUsersByUserId().getEmail(), apply.getJobByJobId().getTitle(),
                apply.getCvByCvid().getUsersByUserId().getFullName(), "deny");
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public boolean check(int JobSeekerID, int JobID) {
        List<Cv> listCv = cvRepository.findByUserId(JobSeekerID);
        if (listCv == null)
            return true;
        for (Cv cv : listCv) {
            if (applyRepository.findForCheck(cv.getId(), JobID) != null)
                return true;
        }
        return false;
    }

    @Override
    public RestResponse listJob(int JobSeekerId) {
        List<Cv> listCv = cvRepository.findByUserId(JobSeekerId);
        if (listCv == null)
            return new RestResponse(false, "Bạn chưa ứng tuyển công việc nào!!!", null);
        List<Job> listJob = new ArrayList<>();
        for (Cv cv : listCv) {
            List<Apply> list = applyRepository.findAppliesByCvid(cv.getId());
            for (Apply apply : list) {
                listJob.add(apply.getJobByJobId());
            }
        }

        for (int i = 0; i < listJob.size(); i++) {
            for (int j = 0; j < listJob.get(i).getAppliesById().size(); j++) {
                for (int k = 0; k < listCv.size(); k++) {
                    if (listJob.get(i).getAppliesById().get(j).getCvid() == listCv.get(k).getId()){
                        List<Apply> applyList = new ArrayList<>();
                        Apply apply = listJob.get(i).getAppliesById().get(j);
                        listJob.get(i).setAppliesById(applyList);
                        applyList.add(apply);
                        listJob.get(i).setAppliesById(applyList);
                    }
                }
            }
        }
        return new RestResponse(true, "Thành công!!!", listJob);
    }

    @Override
    public List<JobDTO> listJobMobile(int JobSeekerId) {
        List<Cv> listCv = cvRepository.findByUserId(JobSeekerId);
        if (listCv == null)
            return null;
        List<JobDTO> listJobDTO = new ArrayList<>();
        for (Cv cv : listCv) {
            List<Apply> list = applyRepository.findAppliesByCvid(cv.getId());
            for (Apply apply : list) {
                JobDTO dto = modelMapper.map(apply.getJobByJobId(), JobDTO.class);
                listJobDTO.add(dto);
            }
        }
        return listJobDTO;
    }


    @Override
    public RestResponse listCv(int JobId, Pageable pageable) {
        Job job = jobRepository.findOne(JobId);
        if (job == null)
            return null;
        List<Apply> listApply = applyRepository.findAppliesByJobId(JobId);
        if (listApply == null)
            return null;
        List<Cv> listCv = new ArrayList<>();
        for (Apply apply : listApply) {
            listCv.add(apply.getCvByCvid());
        }
        Page<Cv> pageCv = new PageImpl<>(listCv, pageable, listCv.size());
        return new RestResponse(true, "Lấy thành công", pageCv);
    }

    @Override
    public RestResponse getAllApply(int userId) {
        List<Cv> cvList = cvRepository.findByUserId(userId);
        List<Apply> applyList = new ArrayList<>();
        int all = 0;
        int not = 0;
        int accept = 0;
        int deny = 0;
        for (int i = 0; i < cvList.size(); i++) {
            applyList.addAll(applyRepository.findAppliesByCvid(cvList.get(i).getId()));
        }
        for (int i = 0; i < applyList.size(); i++) {
            all++;
        }
        for (int i = 0; i < applyList.size(); i++) {
            if (applyList.get(i).getStatus().equals("1")) {
                not++;
            }
        }
        for (int i = 0; i < applyList.size(); i++) {
            if (applyList.get(i).getStatus().equals("2")) {
                accept++;
            }
        }
        for (int i = 0; i < applyList.size(); i++) {
            if (applyList.get(i).getStatus().equals("3")) {
                deny++;
            }
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("all", all);
        map.put("not", not);
        map.put("accept", accept);
        map.put("deny", deny);
        return new RestResponse(true, "Get Apply Success", map);
    }

    @Override
    public int getAllApplyMobile(int userId) {
        List<Cv> cvList = cvRepository.findByUserId(userId);
        List<Apply> applyList = new ArrayList<>();
        int all = 0;
        for (int i = 0; i < cvList.size(); i++) {
            applyList.addAll(applyRepository.findAppliesByCvid(cvList.get(i).getId()));
        }
        for (int i = 0; i < applyList.size(); i++) {
            all++;
        }
        return all;
    }

    @Override
    public RestResponse checkStatusApply(int CvId, int JobId) {
        Apply apply = applyRepository.findForCheck(CvId, JobId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra vui lòng thử lại!!!", null);
        return new RestResponse(true, "Thành công!!!", apply.getStatus());
    }
}
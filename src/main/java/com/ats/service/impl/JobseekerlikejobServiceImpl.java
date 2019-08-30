package com.ats.service.impl;

import com.ats.dto.JobDTO;
import com.ats.entity.Job;
import com.ats.entity.Jobseekerlikejob;
import com.ats.entity.Users;
import com.ats.repository.JobRepository;
import com.ats.repository.JobseekerlikejobRespository;
import com.ats.repository.UsersRepository;
import com.ats.service.JobseekerlikejobService;
import com.ats.util.RestResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JobseekerlikejobServiceImpl implements JobseekerlikejobService {
    @Autowired
    private JobseekerlikejobRespository jobseekerlikejobRespository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JobRepository jobRepository;
    //Mapping Object
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean check(int JobSeekerId, int Jobid) {
        List<Jobseekerlikejob> jobseekerlikejob = jobseekerlikejobRespository.findByUserIdAndJobId(JobSeekerId, Jobid);
        if (jobseekerlikejob.size() > 0 )
            return false;
        return true;
    }

    @Override
    public RestResponse create(int JobSeekerId, int JobId) {
        Users user = usersRepository.findOne(JobSeekerId);
        Job job = jobRepository.findOne(JobId);
        if (user == null || job == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử laij1!!", null);
        Jobseekerlikejob jobseekerlikejob = new Jobseekerlikejob();
        jobseekerlikejob.setJobByJobId(job);
        jobseekerlikejob.setUsersByUserId(user);
        jobseekerlikejob.setCreatedDate(new Timestamp(new Date().getTime()));
        jobseekerlikejob.setJobId(JobId);
        jobseekerlikejob.setUserId(JobId);
        jobseekerlikejobRespository.save(jobseekerlikejob);
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public RestResponse listJob(int JobSeekerId) {
        Users user = usersRepository.findOne(JobSeekerId);
        if (user == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại", null);
        List<Jobseekerlikejob> list = jobseekerlikejobRespository.findJobseekerlikejobsByUserId(JobSeekerId);
        if (list == null)
            return new RestResponse(false, "Bạn chưa lưu công việc yêu thích nào!!!", null);
        List<Job> listJob = new ArrayList<>();
        for (Jobseekerlikejob jobseekerlikejob : list) {
            listJob.add(jobseekerlikejob.getJobByJobId());
        }
        return new RestResponse(true, "Thành công!!!", listJob);
    }

    @Override
    public List<JobDTO> listJobMobile(int JobSeekerId) {
        List<Jobseekerlikejob> list = jobseekerlikejobRespository.findJobseekerlikejobsByUserId(JobSeekerId);
        if (list == null)
            return null;
        List<Job> listJob = new ArrayList<>();
        List<JobDTO> listJobDTO = new ArrayList<>();
        for (Jobseekerlikejob jobseekerlikejob : list) {
            JobDTO dto = modelMapper.map(jobseekerlikejob.getJobByJobId(), JobDTO.class);
            listJobDTO.add(dto);
        }
        return listJobDTO;
    }

    @Override
    public RestResponse unCheck(int JobSeekerId, int JobId) {
        Users user = usersRepository.findOne(JobSeekerId);
        Job job = jobRepository.findOne(JobId);
        if (user == null || job == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử laij1!!", null);
        List<Jobseekerlikejob> jobseekerlikejob = jobseekerlikejobRespository.findByUserIdAndJobId(JobSeekerId, JobId);
        if (jobseekerlikejob == null)
            return new RestResponse(true, "Thành công!!!", null);
        jobseekerlikejobRespository.delete(jobseekerlikejob);
        return new RestResponse(true, "Thành công!!!", null);
    }

}

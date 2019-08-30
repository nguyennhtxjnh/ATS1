package com.ats.service.impl;

import com.ats.dto.*;
import com.ats.entity.*;
import com.ats.repository.ApplyRepository;
import com.ats.repository.CVRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.*;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    CityService cityService;
    @Autowired
    SkillService skillService;
    @Autowired
    JoblevelService joblevelService;
    @Autowired
    CVRepository cvRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CountjobService countjobService;
    @Autowired
    ApplyService applyService;
    @Autowired
    ApplyRepository applyRepository;
    @Autowired
    EmailService emailService;

    private static final Logger LOGGER = LogManager.getLogger(JobServiceImpl.class);

    @Override
    public int createJob(JobDTO2 job) {
        LOGGER.info("Begin createJob in Job Service with job name : {}", job.getTitle());
        int result = -1;
        Job newJob;
        try {
            ModelMapper mapper = new ModelMapper();
            newJob = mapper.map(job, Job.class);
            City city = new City();
            city.setId(newJob.getCityId());

            Company company = new Company();
            company.setId(newJob.getCompanyId());

            Users users = new Users();
            users.setId(newJob.getUserId());

            Joblevel joblevel = new Joblevel();
            joblevel.setId(newJob.getJobLevelId());

            Industry industry = new Industry();
            industry.setId(newJob.getIndustryId());

            newJob.setCityByCityId(city);
            newJob.setUsersByUserId(users);
            newJob.setCompanyByCompanyId(company);
            newJob.setJoblevelByJobLevelId(joblevel);
            newJob.setIndustryByIndustryId(industry);
//            newJob = jobRepository.save(newJob);
            result = jobRepository.save(newJob).getId();
            System.out.println("KQ : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End createJob in Job Service with job name : {}", job.getTitle());
        return result;
    }

    @Override
    public int updateJob(JobDTO2 job) {
        LOGGER.info("Begin updateJob in Job Service with job name : {}", job.getTitle());
        int result = 0;
        Job newJob;
        try {
            newJob = jobRepository.findOne(job.getId());
            newJob.setTitle(job.getTitle());
            newJob.setJobDescription(job.getJobDescription());
            newJob.setYearExperience(job.getYearExperience());
            newJob.setSalaryFrom(job.getSalaryFrom());
            newJob.setSalaryTo(job.getSalaryTo());
            newJob.setAddress(job.getAddress());
            System.out.println(job.getSalaryFrom());
            System.out.println(job.getSalaryTo());


            City city = new City();
            city.setId(job.getCityId());

            Company company = new Company();
            company.setId(job.getCompanyId());

            Users users = new Users();
            users.setId(job.getUserId());

            Joblevel joblevel = new Joblevel();
            joblevel.setId(job.getJoblevelId());

            Industry industry = new Industry();
            industry.setId(job.getIndustryId());

            newJob.setCityByCityId(city);
            newJob.setUsersByUserId(users);
            newJob.setCompanyByCompanyId(company);
            newJob.setJoblevelByJobLevelId(joblevel);
            newJob.setIndustryByIndustryId(industry);

            newJob = jobRepository.save(newJob);
            result = newJob.getId();
            System.out.println("KQ : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End updateJob in Job Service with job name : {}", job.getTitle());
        return result;
    }

    @Override
    public Page<JobDTO> searchJob(String job, String city, String industry, Pageable pageable) {
        LOGGER.info("Begin searchJob in Job Service with job name : {} ", job);
        Page<Job> pageOfJob;
        Page<JobDTO> pageDTO = null;
        try {
            LOGGER.info("Begin searchJob in Job Repository with job name : {} ", job);
            System.out.println("String search : " + job);
            System.out.println("City : " + city);
            System.out.println("Industry : " + industry);
            pageOfJob = jobRepository.searchJob(job, pageable, "approved", new Date(), city, industry);
            System.out.println(pageOfJob.getContent().size());
            ModelMapper mapper = new ModelMapper();
            pageDTO = pageOfJob.map(new Converter<Job, JobDTO>() {
                @Override
                public JobDTO convert(Job job) {
                    JobDTO dto;
                    dto = mapper.map(job, JobDTO.class);
                    return dto;
                }
            });

            LOGGER.info("End searchJob in Job Repository with job list size : {} ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in Job Service with job list size : {} ");
        return pageDTO;
    }

    @Override
    public List<String> getALlJobTitle() {
        LOGGER.info("Begin getALlJobTitle in Job Service");
        List<Job> listofJob;
        List<String> listofResult = new ArrayList<>();

        try {
            LOGGER.info("Begin getALlJobTitle in Job Repository ");
            listofJob = jobRepository.findAll();
            LOGGER.info("End getALlJobTitle in Job Repository");
            for (int i = 0; i < listofJob.size(); i++) {
                if (listofJob.get(i).getStatus().matches("approved") &&
                        listofJob.get(i).getEndDateForApply().after(new Date())) {
                    listofResult.add(listofJob.get(i).getTitle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getALlJobTitle in Job Service");
        return listofResult;
    }

    @Override
    public Page<JobDTO> getTop8(Pageable pageable) {
        LOGGER.info("Begin getTop8 in Job Service");
        Page<Job> listofJob = null;
        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            LOGGER.info("Begin getTop8 in Job Repository ");
            listofJob = jobRepository.getTop8(pageable, "approved", new Date());

            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob.getContent(), targetListType);
            if (listofDTO.size() < 8) {
                pageDTO = new PageImpl<>(listofDTO.subList(0, listofDTO.size()), new PageRequest(0, 10), listofDTO.size());
            }
            if (listofDTO.size() >= 8) {
                pageDTO = new PageImpl<>(listofDTO.subList(0, 8), new PageRequest(0, 10), 8);
            }

            LOGGER.info("End getTop8 in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service");
        return pageDTO;
    }

    @Override
    public Page<JobDTO> getJobByCompanyId(Pageable pageable, int companyId) {
        LOGGER.info("Begin getJobByCompanyId in Job Service");
        Page<Job> listofJob = null;
        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            LOGGER.info("Begin getJobByCompanyId in Job Repository ");
            listofJob = jobRepository.getJobByCompanyId(pageable, companyId, "approved");
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob.getContent(), targetListType);
            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
            LOGGER.info("End getJobByCompanyId in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobByCompanyId in Job Service");
        return pageDTO;
    }

    @Override
    public List<JobDTO> getTop8Mobile() {
        List<Job> listofJob = null;
        List<JobDTO> listofDTO = null;
        try {
            LOGGER.info("Begin getTop8 in Job Repository ");
            listofJob = jobRepository.getTop8Mobile("approved", new Date());
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob, targetListType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service");
        if(listofDTO.size() < 8){
            return listofDTO;
        }
        return listofDTO.subList(0,8);
    }

    @Override
    public Page<JobDTO> getJobByEmployerId(int employerId, Pageable pageable, String status) {
        LOGGER.info("Begin getJobByEmployerId in Job Service");
        Page<Job> listofJob = null;
        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            LOGGER.info("Begin getJobByEmployerId in Job Repository ");
            listofJob = jobRepository.findAllByEmployerId(pageable, status, employerId);
            System.out.println(listofJob.getContent().size());
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob.getContent(), targetListType);
            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
            LOGGER.info("End getJobByEmployerId in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobByEmployerId in Job Service");
        return pageDTO;
    }

    @Override
    public HashMap getJobListByEmployerId(int employerId) {
        LOGGER.info("Begin getJobListByEmployerId in Job Service");
        List<Job> listOfJob;
        List<Apply> applyList = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        try {
            LOGGER.info("Begin getJobListByEmployerId in Job Repository ");
            listOfJob = jobRepository.getJobsByEmployerId(employerId);
            System.out.println(listOfJob.size());
            int all = 0;
            int current = 0;
            int deny = 0;
            int wait = 0;
            int js = 0;
            for (int i = 0; i < listOfJob.size(); i++) {
                all++;
            }
            for (int i = 0; i < listOfJob.size(); i++) {
                if (listOfJob.get(i).getStatus().equals("approved")) {
                    current++;
                }
            }
            for (int i = 0; i < listOfJob.size(); i++) {
                if (listOfJob.get(i).getStatus().equals("ban")) {
                    deny++;
                }
            }
            for (int i = 0; i < listOfJob.size(); i++) {
                if (listOfJob.get(i).getStatus().equals("new")) {
                    wait++;
                }
            }
            map.put("all", all);
            map.put("current", current);
            map.put("deny", deny);
            map.put("wait", wait);
            for (int i = 0; i < listOfJob.size(); i++) {
                if (listOfJob.get(i).getStatus().equals("approved")) {
                    applyList.addAll(applyRepository.findAppliesByJobId(listOfJob.get(i).getId()));
                }

            }
            for (int i = 0; i < applyList.size(); i++) {
                System.out.println(applyList.get(i).getStatus());
                if (applyList.get(i).getStatus().equals("1")) {
                    js++;
                }
            }
            map.put("allJS", js);
            LOGGER.info("End getJobByEmployerId in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobByEmployerId in Job Service");
        return map;
    }

    @Override
    public JobDTO3 getJobDetail(int id) {
        LOGGER.info("Begin getJobDetail in Job Service with id : " + id);
        Job job;
        JobDTO3 jobDTO = null;
        try {
            LOGGER.info("Begin getJobDetail in Job Repository with id : " + id);
            job = jobRepository.findOne(id);
            LOGGER.info("End getJobDetail in Job Repository with id : " + id);
            List<Job> listJobOfCompany = jobRepository.getJobByCompanyID(job.getCompanyId(), job.getId());
            List<String> listSkillName = skillService.getSkillName(job.getSkillneedforjobsById());
            String jobLevelName = joblevelService.getJobLevelNameById(job.getJobLevelId());
            ModelMapper mapper = new ModelMapper();
            jobDTO = mapper.map(job, JobDTO3.class);

            jobDTO.setListSkillName(listSkillName);
            jobDTO.setListJobSameCompany(listJobOfCompany);
            jobDTO.setJoblevelName(jobLevelName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobDetail in Job Service with id : " + id);
        return jobDTO;
    }

    @Override
    public Job getJobDetailToUpdate(int id) {
        LOGGER.info("Begin getJobDetailToUpdate in Job Service with id : " + id);
        Job job = null;

        try {
            LOGGER.info("Begin getJobDetailToUpdate in Job Repository with id : " + id);
            job = jobRepository.findOne(id);
            LOGGER.info("End getJobDetailToUpdate in Job Repository with id : " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobDetailToUpdate in Job Service with id : " + id);
        return job;
    }

    @Override
    public JobDTO3 getJobDetail(int id, int userId) {
        LOGGER.info("Begin getJobDetail in Job Service with id : " + id);
        Job job;
        JobDTO3 jobDTO = null;

        try {
            LOGGER.info("Begin getJobDetail in Job Repository with id : " + id);
            job = jobRepository.findOne(id);
            System.out.println("abcyxcjalksdlkajdlksad : " + userId);
            if (userId != 0) {
                countjobService.countWhenEmployerGetDetailOfJob(id, userId);
            }

            LOGGER.info("End getJobDetail in Job Repository with id : " + id);
            List<Job> listJobOfCompany = jobRepository.getJobByCompanyID(job.getCompanyId(), job.getId());
            List<String> listSkillName = skillService.getSkillName(job.getSkillneedforjobsById());
            String jobLevelName = joblevelService.getJobLevelNameById(job.getJobLevelId());
            ModelMapper mapper = new ModelMapper();
            jobDTO = mapper.map(job, JobDTO3.class);

            jobDTO.setListSkillName(listSkillName);
            jobDTO.setListJobSameCompany(listJobOfCompany);
            jobDTO.setJoblevelName(jobLevelName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobDetail in Job Service with id : " + id);
        return jobDTO;
    }

    @Override
    public int changeStatus(int id, String newStatus) {
        LOGGER.info("Begin changeStatus in Job Service with Job id - newStatus : {}", id + newStatus);
        int success;
        success = jobRepository.changeStatus(id, newStatus);
        Job job = jobRepository.findOne(id);

        if (job.getEndDateForApply() == null && newStatus.equals("approved")) {
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            job.setCreatedDate(new Timestamp(c.getTimeInMillis()));
            c.add(Calendar.DATE, 30);
            job.setEndDateForApply(new Timestamp(c.getTimeInMillis()));
            job.setStatus(newStatus);
            jobRepository.save(job);
        }

        emailService.sendEmailStatus(job.getUsersByUserId().getEmail(), job.getTitle(),
                job.getUsersByUserId().getFullName(), newStatus, "job");
        LOGGER.info("End changeStatus in Job Service with result: {}", success);
        return success;
    }

    @Override
    public Page<JobDTO> suggestJob(int cvId, Pageable pageable) {
        LOGGER.info("Begin suggestJob in Job Service with cvId : " + cvId);
        List<Job> jobList;
        List<SuggestDTO> suggestDTOS = new ArrayList<>();
        List<Job> suggestJobList = new ArrayList<>();
        Page<Job> jobPage;
        List<Skill> skillObjinCv = new ArrayList<>();
        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            Cv cv = cvRepository.findOne(cvId);
            Users users = cv.getUsersByUserId();
            List<Apply> applies = null;
            for (int i = 0; i < users.getCvsById().size(); i++) {
                applies = applyRepository.findAppliesByCvid(users.getCvsById().get(i).getId());
            }

            List<Job> jobs = new ArrayList<>();
            if (applies != null) {
                for (int i = 0; i < applies.size(); i++) {
                    jobs.add(applies.get(i).getJobByJobId());
                }
            }
            for (int i = 0; i < cv.getSkillincvsById().size(); i++) {
                skillObjinCv.add(cv.getSkillincvsById().get(i).getSkillBySkillId());
            }
            Date date = new Date();

            String cityName = cv.getCityByCityId().getFullName();
            String industryName = cv.getIndustryByIndustryId().getName();

            jobPage = jobRepository.suggestJob(cv.getYearExperience(), industryName,
                    cityName, "approved", date, pageable);
            System.out.println("Lần 1 : " + jobPage.getContent().size());
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        cityName, "approved", date, pageable);
                System.out.println("Lần 2 : " + jobPage.getContent().size());
            }
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        "", "approved", date, pageable);
                System.out.println("Lần 3 : " + jobPage.getContent().size());
            }
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                System.out.println("Lần 4 : " + jobPage.getContent().size());
            }

            jobList = jobPage.getContent();
            System.out.println("Size cuối cùng : " + jobList.size());
            for (int i = 0; i < jobList.size(); i++) {
                int check = 0;
                for (int j = 0; j < skillObjinCv.size(); j++) {
                    for (int k = 0; k < jobList.get(i).getSkillneedforjobsById().size(); k++) {
                        if (skillObjinCv.get(j).getSkillMasterId() ==
                                jobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillMasterId()) {
                            if (skillObjinCv.get(j).getSkillLevel() >=
                                    jobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillLevel()) {
                                check++;
                            }
                        }
                    }
                }
                if (check == jobList.get(i).getSkillneedforjobsById().size()) {
                    suggestJobList.add(jobList.get(i));
                }
            }
            for (int i = 0; i < suggestJobList.size(); i++) {
                int sum = 0;

                for (int j = 0; j < skillObjinCv.size(); j++) {
                    boolean flag = true;
                    for (int k = 0; k < suggestJobList.get(i).getSkillneedforjobsById().size(); k++) {
                        if (skillObjinCv.get(j).getSkillMasterId() ==
                                suggestJobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillMasterId()) {
                            sum += skillObjinCv.get(j).getSkillLevel() -
                                    suggestJobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillLevel();
                            flag = false;
                        }
                    }
                    if (flag) {
                        sum += skillObjinCv.get(j).getSkillLevel();
                    }
                }
                SuggestDTO dto = new SuggestDTO();
                dto.setSum(sum);
                dto.setJob(suggestJobList.get(i));
                suggestDTOS.add(dto);
            }
            Collections.sort(suggestDTOS);
            suggestJobList.clear();
            for (int i = 0; i < suggestDTOS.size(); i++) {
                System.out.println(i + "-" + suggestDTOS.get(i).getSum());
                System.out.println(i + "-" + suggestDTOS.get(i).getJob().getTitle());
                suggestJobList.add(suggestDTOS.get(i).getJob());
            }
            if (suggestJobList.size() < 10) {
                for (int i = 0; i < jobList.size(); i++) {
                    if (!suggestJobList.contains(jobList.get(i))) {
                        suggestJobList.add(jobList.get(i));
                    }
                }
            }
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(suggestJobList, targetListType);

            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End suggestJob in Job Service with cvId : " + cvId);
        return pageDTO;
    }

    @Override
    public Page<JobDTO> suggestJobByJobId(int jobid, Pageable pageable) {
        LOGGER.info("Begin suggestJob in Job Service with jobid : " + jobid);
        List<Skill> skillObjinCv = new ArrayList<>();
        List<Job> jobList;
        List<SuggestDTO> suggestDTOS = new ArrayList<>();
        List<Job> suggestJobList = new ArrayList<>();
        Page<Job> jobPage;

        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            Job job = jobRepository.findOne(jobid);
            System.out.println("SKILL của Job Ở ĐÂY");
            for (int i = 0; i < job.getSkillneedforjobsById().size(); i++) {
                skillObjinCv.add(job.getSkillneedforjobsById().get(i).getSkillBySkillId());
            }
            Date date = new Date();

            String cityName = job.getCityByCityId().getFullName();
            String industryName = job.getIndustryByIndustryId().getName();

            jobPage = jobRepository.suggestJob(100, industryName,
                    cityName, "approved", date, pageable);
            System.out.println("Lần 1 : " + jobPage.getContent().size());
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        cityName, "approved", date, pageable);
                System.out.println("Lần 2 : " + jobPage.getContent().size());
            }
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        "", "approved", date, pageable);
                System.out.println("Lần 3 : " + jobPage.getContent().size());
            }
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                System.out.println("Lần 4 : " + jobPage.getContent().size());
            }

            jobList = jobPage.getContent();
            System.out.println("Size cuối cùng 1: " + jobList.size());

            List<Job> jobList1 = new ArrayList<>();
            for (int i = 0; i < jobList.size(); i++) {
                if(jobList.get(i).getId() != job.getId()){
                    jobList1.add(jobList.get(i));
                }
            }
            System.out.println("Size cuối cùng 2: " + jobList1.size());
            for (int i = 0; i < jobList1.size(); i++) {
                int check = 0;
                for (int j = 0; j < skillObjinCv.size(); j++) {
                    for (int k = 0; k < jobList1.get(i).getSkillneedforjobsById().size(); k++) {
                        if (skillObjinCv.get(j).getSkillMasterId() ==
                                jobList1.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillMasterId()) {
                            if (skillObjinCv.get(j).getSkillLevel() >=
                                    jobList1.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillLevel()) {
                                check++;
                            }
                        }
                    }
                }
                if (check == jobList1.get(i).getSkillneedforjobsById().size()) {
                    suggestJobList.add(jobList1.get(i));
                }
            }
            for (int i = 0; i < suggestJobList.size(); i++) {
                int sum = 0;

                for (int j = 0; j < skillObjinCv.size(); j++) {
                    boolean flag = true;
                    for (int k = 0; k < suggestJobList.get(i).getSkillneedforjobsById().size(); k++) {
                        if (skillObjinCv.get(j).getSkillMasterId() ==
                                suggestJobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillMasterId()) {
                            sum += skillObjinCv.get(j).getSkillLevel() -
                                    suggestJobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillLevel();
                            flag = false;
                        }
                    }
                    if (flag) {
                        sum += skillObjinCv.get(j).getSkillLevel();
                    }
                }
                SuggestDTO dto = new SuggestDTO();
                dto.setSum(sum);
                dto.setJob(suggestJobList.get(i));
                suggestDTOS.add(dto);
            }
            Collections.sort(suggestDTOS);
            suggestJobList.clear();
            for (int i = 0; i < suggestDTOS.size(); i++) {
                System.out.println(i + "-" + suggestDTOS.get(i).getSum());
                System.out.println(i + "-" + suggestDTOS.get(i).getJob().getTitle());
                suggestJobList.add(suggestDTOS.get(i).getJob());
            }
            if (suggestJobList.size() < 10) {
                for (int i = 0; i < jobList.size(); i++) {
                    if (!suggestJobList.contains(jobList.get(i))) {
                        suggestJobList.add(jobList.get(i));
                    }
                }
            }
            System.out.println("Danh sach goi y : " + suggestJobList.size());

            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(suggestJobList, targetListType);

            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End suggestJob in Job Service with jobid : " + jobid);
        return pageDTO;
    }

    @Override
    public Page<JobDTO> suggestJobByUserId(int userid, Pageable pageable) {
        LOGGER.info("Begin suggestJob in Job Service with userid : " + userid);
        List<Skill> skillObjinCv = new ArrayList<>();
        List<Job> jobList;
        List<Job> suggestJobList = new ArrayList<>();
        Page<Job> jobPage;
        Cv cv = null;
        List<SuggestDTO> suggestDTOS = new ArrayList<>();
        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            Users users = usersRepository.findOne(userid);
            List<Apply> applies = null;
            for (int i = 0; i < users.getCvsById().size(); i++) {
                applies = applyRepository.findAppliesByCvid(users.getCvsById().get(i).getId());
                if (users.getCvsById().get(i).getIsActive() == 1) {
                    cv = cvRepository.findOne(users.getCvsById().get(i).getId());
                }
            }

            List<Job> jobs = new ArrayList<>();
            if (applies != null) {
                for (int i = 0; i < applies.size(); i++) {
                    jobs.add(applies.get(i).getJobByJobId());
                }
            }
            for (int i = 0; i < cv.getSkillincvsById().size(); i++) {
                skillObjinCv.add(cv.getSkillincvsById().get(i).getSkillBySkillId());
            }
            Date date = new Date();

            String cityName = cv.getCityByCityId().getFullName();
            String industryName = cv.getIndustryByIndustryId().getName();

            jobPage = jobRepository.suggestJob(cv.getYearExperience(), industryName,
                    cityName, "approved", date, pageable);
            System.out.println("Lần 1 : " + jobPage.getContent().size());
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        cityName, "approved", date, pageable);
                System.out.println("Lần 2 : " + jobPage.getContent().size());
            }
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        "", "approved", date, pageable);
                System.out.println("Lần 3 : " + jobPage.getContent().size());
            }
            if (jobPage.getContent().size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                System.out.println("Lần 4 : " + jobPage.getContent().size());
            }

            jobList = jobPage.getContent();
            System.out.println("Size sau Hard Condition : " + jobList.size());
            for (int i = 0; i < jobList.size(); i++) {
                int check = 0;
                for (int j = 0; j < skillObjinCv.size(); j++) {
                    for (int k = 0; k < jobList.get(i).getSkillneedforjobsById().size(); k++) {
                        if (skillObjinCv.get(j).getSkillMasterId() ==
                                jobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillMasterId()) {
                            if (skillObjinCv.get(j).getSkillLevel() >=
                                    jobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillLevel()) {
                                check++;
                            }
                        }
                    }
                }
                if (check == jobList.get(i).getSkillneedforjobsById().size()) {
                    suggestJobList.add(jobList.get(i));
                }
            }
            System.out.println("Size sau Soft Condition Phase 1 : " + suggestJobList.size());
            System.out.println("đã apply : " + jobs.size());
            if (jobs.size() > 0) {
                for (int i = 0; i < jobs.size(); i++) {
                    if (suggestJobList.contains(jobs.get(i))) {
                        suggestJobList.remove(jobs.get(i));
                    }
                }
            }
            for (int i = 0; i < suggestJobList.size(); i++) {
                int sum = 0;

                for (int j = 0; j < skillObjinCv.size(); j++) {
                    boolean flag = true;
                    for (int k = 0; k < suggestJobList.get(i).getSkillneedforjobsById().size(); k++) {
                        if (skillObjinCv.get(j).getSkillMasterId() ==
                                suggestJobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillMasterId()) {
                            sum += skillObjinCv.get(j).getSkillLevel() -
                                    suggestJobList.get(i).getSkillneedforjobsById().get(k).getSkillBySkillId().getSkillLevel();
                            flag = false;
                        }
                    }
                    if (flag) {
                        sum += skillObjinCv.get(j).getSkillLevel();
                    }
                }
                SuggestDTO dto = new SuggestDTO();
                dto.setSum(sum);
                dto.setJob(suggestJobList.get(i));
                suggestDTOS.add(dto);
            }
            Collections.sort(suggestDTOS);
            suggestJobList.clear();
            for (int i = 0; i < suggestDTOS.size(); i++) {
                System.out.println(i + "-" + suggestDTOS.get(i).getSum());
                System.out.println(i + "-" + suggestDTOS.get(i).getJob().getTitle());
                suggestJobList.add(suggestDTOS.get(i).getJob());
            }
            System.out.println("Size sau Soft Condition Phase 2 : " + suggestJobList.size());
            if (suggestJobList.size() < 10) {
                for (int i = 0; i < jobList.size(); i++) {
                    if (!suggestJobList.contains(jobList.get(i)) && !jobs.contains(jobList.get(i))) {
                        suggestJobList.add(jobList.get(i));
                    }
                }
            }
            System.out.println("Size sau khi bổ sung : " + suggestJobList.size());

            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(suggestJobList, targetListType);

            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End suggestJob in Job Service with userid : " + userid);
        return pageDTO;
    }

    public RestResponse findListJobValid(int EmployerId) {
        List<Job> listJob = jobRepository.getJobValid(EmployerId, new Timestamp(new Date().getTime()), "approved");
        Page<Job> jobPage;
        Users users = usersRepository.findOne(EmployerId);
        if (users.getRoleId() == 2) {
            int companyId = listJob.get(0).getCompanyId();
            jobPage = jobRepository.getInvalidJobByCompanyId(null, companyId, "approved", new Timestamp(new Date().getTime()));
            listJob = jobPage.getContent();

        }
        if (listJob == null)
            return new RestResponse(false, "không có công việc nào!!!", null);
        return new RestResponse(true, "Thành công!!!", listJob);
    }

    @Override
    public RestResponse findListJobInValid(int EmployerId) {
        List<Job> listJob = jobRepository.getJobInValid(EmployerId, new Timestamp(new Date().getTime()));
        if (listJob == null)
            return new RestResponse(false, "Không có công việc nào!!", null);
        return new RestResponse(true, "Thành công!!!", listJob);
    }

    @Override
    public RestResponse listJobsByEmployerId(int EmployerId) {
        List<Job> listJob = jobRepository.getJobInValid(EmployerId, new Timestamp(new Date().getTime()));
        Page<Job> jobPage;
        List<MainEmpJobDTO> mainEmpJobDTOS = new ArrayList<>();
        Users users = usersRepository.findOne(EmployerId);
        if (users.getRoleId() == 2) {
            int companyId = listJob.get(0).getCompanyId();
            jobPage = jobRepository.getJobByCompanyId(null, companyId, "approved");
            listJob = jobPage.getContent();
            for (int i = 0; i < listJob.size(); i++) {
                MainEmpJobDTO dto = new MainEmpJobDTO();
                dto.setJobId(listJob.get(i).getId());
                dto.setFullName(listJob.get(i).getUsersByUserId().getFullName());
                mainEmpJobDTOS.add(dto);
            }
        }
        if (listJob == null)
            return new RestResponse(false, "Không có công việc nào!!", null);
        return new RestResponse(true, "Thành công!!!", mainEmpJobDTOS);
    }

    public Page<Job> getAllJob(Pageable pageable, String search, String status) {
        LOGGER.info("Begin getAllJob in Job Service");
        Page<Job> listofJob = null;
        try {
            LOGGER.info("Begin getAllJob in Job Repository ");
            System.out.println(search);
            listofJob = jobRepository.getAll(pageable, search, status);
            System.out.println(listofJob.getContent().size());
            LOGGER.info("End getAllJob in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllJob in Job Service");
        return listofJob;
    }

}

package com.ats.service.impl;

import com.ats.dto.*;
import com.ats.entity.*;
import com.ats.repository.*;
import com.ats.service.*;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CVServiceImpl implements CVService {
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private EducationService educationService;
    @Autowired
    private CertificationService certificationService;
    @Autowired
    private WorkexperienceService workexperienceService;
    @Autowired
    private SocialactivityService socialactivityService;
    @Autowired
    private ProjectorproductworkedService projectorproductworkedService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private SkillincvRepository skillincvRepository;
    @Autowired
    private SkillmasterRepository skillmasterRepository;
    @Autowired
    private CertificationRepository certificationRepository;
    @Autowired
    private SocialactivityRepository socialactivityRepository;
    @Autowired
    private WorkexperienceRepository workexperienceRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ProjectorproductworkedRepository projectorproductworkedRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private UserlikecvRepository userlikecvRepository;

    //Mapping Object
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RestResponse getCVByCVID(int id) {
        Cv cv = cvRepository.findOne(id);
        ModelMapper mapper = new ModelMapper();
        CVDTO dto = mapper.map(cv, CVDTO.class);
        if (cv == null)
            return new RestResponse(false, "CV này không tồn tại!!!", cv);
        return new RestResponse(true, "Tải CV thành công!!!", dto);
    }

    @Override
    public RestResponse create(CVDTO newCV) {
        try {
            Cv cv = modelMapper.map(newCV, Cv.class);
            cv.setCityByCityId(cityRepository.findOne(newCV.getCityId()));
            cv.setIndustryByIndustryId(industryRepository.findOne(newCV.getIndustryId()));
            cv.setUsersByUserId(usersRepository.findOne(newCV.getUserId()));
            // check đã có CV nào được Active tìm việc chưa
            if (checkIsActive(newCV.getUserId()))
                cv.setIsActive(1);
            else
                cv.setIsActive(0);
            cv.setStatus("1");
            cv.setCreatedDate(new Timestamp(new Date().getTime()));
            cv.setLastModify(new Timestamp(new Date().getTime()));
            int CVID = cvRepository.save(cv).getId();
            Cv saveCv = cvRepository.findOne(CVID);

            // mapping Certification
            List<CertificationDTO> listCer = newCV.getCertificationsById();
            if (listCer != null) {
                for (CertificationDTO certificationDTO : listCer) {
                    Certification cer = modelMapper.map(certificationDTO, Certification.class);
                    cer.setCvid(CVID);
                    cer.setCvByCvid(saveCv);
                    certificationService.createANewCertification(cer);
                }
            }
            // mapping Education
            List<EducationDTO> listEdu = newCV.getEducationsById();
            if (listEdu != null) {
                for (EducationDTO educationDTO : listEdu) {
                    Education edu = modelMapper.map(educationDTO, Education.class);
                    edu.setCvid(CVID);
                    edu.setCvByCvid(saveCv);
                    educationService.createANewEducation(edu);
                }
            }
            // mapping SocialActivity
            List<SocialactivitiesDTO> listAct = newCV.getSocialactivitiesById();
            if (listAct != null) {
                for (SocialactivitiesDTO socialactivitiesDTO : listAct) {
                    Socialactivities soc = modelMapper.map(socialactivitiesDTO, Socialactivities.class);
                    soc.setCvid(CVID);
                    soc.setCvByCvid(saveCv);
                    socialactivityService.createANewSocialactivity(soc);
                }
            }
            // mapping WorkExperience
            List<WorkexperienceDTO> listWor = newCV.getWorkexperiencesById();
            if (listWor != null) {
                for (WorkexperienceDTO workexperienceDTO : listWor) {
                    Workexperience wor = modelMapper.map(workexperienceDTO, Workexperience.class);
                    wor.setCvid(CVID);
                    wor.setCvByCvid(saveCv);
                    workexperienceService.createANewWorkExperience(wor);
                }
            }
            //mapping ProjectOrProduct
            List<ProjectorproductworkedDTO> listPro = newCV.getProjectorproductworkedsById();
            if (listPro != null) {
                for (ProjectorproductworkedDTO projectorproductworkedDTO : listPro) {
                    Projectorproductworked pro = modelMapper.map(projectorproductworkedDTO, Projectorproductworked.class);
                    pro.setCvid(CVID);
                    pro.setCvByCvid(saveCv);
                    projectorproductworkedService.createANewProjectorProduct(pro);
                }
            }
            // mapping skill
            addSkill(newCV, saveCv);
            return new RestResponse(true, "Bạn đã tạo CV thành công!!!", CVID);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return new RestResponse(false, "Tạo CV không thành công. Vui lòng thử lại!!!", null);
    }

    @Override
    public RestResponse edit(CVDTO editedCv) {
        try {
            Cv cv = cvRepository.findOne(editedCv.getId());
            if (cv == null)
                return new RestResponse(false, "Không thành công!!!", null);
            System.out.println(editedCv.getId());
            List<Skillincv> listOldSkill = skillincvRepository.findSkillincvsByCvid(editedCv.getId());
            skillincvRepository.delete(listOldSkill);
            Cv editCv = modelMapper.map(editedCv, Cv.class);
            editCv.setCityByCityId(cityRepository.findOne(editedCv.getCityId()));
            editCv.setIndustryByIndustryId(industryRepository.findOne(editedCv.getIndustryId()));
            editCv.setUsersByUserId(usersRepository.findOne(editedCv.getUserId()));
            editCv.setLastModify(new Timestamp(new Date().getTime()));
            int CVID = cv.getId();

            List<CertificationDTO> listCer = editedCv.getCertificationsById();
            List<Certification> certificationList = new ArrayList<>();
            certificationList = certificationRepository.findListCertificationByCVID(editedCv.getId());
            certificationRepository.delete(certificationList);
            certificationList.clear();
            if (listCer != null) {
                for (CertificationDTO certificationDTO : listCer) {
                    Certification cer = modelMapper.map(certificationDTO, Certification.class);
                    cer.setCvid(CVID);
                    cer.setCvByCvid(cv);
                    cer = certificationRepository.save(cer);
                    System.out.println("Certification id : " + cer.getId());
                    cer.setId(cer.getId());
                    certificationList.add(cer);
                }
            }

            List<EducationDTO> listEdu = editedCv.getEducationsById();
            List<Education> educationList = new ArrayList<>();
            educationList = educationRepository.findAllEduByCVID(editCv.getId());
            educationRepository.delete(educationList);
            educationList.clear();
            if (listEdu != null) {
                for (EducationDTO educationDTO : listEdu) {
                    Education edu = modelMapper.map(educationDTO, Education.class);
                    edu.setCvid(CVID);
                    edu.setCvByCvid(cv);
                    edu = educationRepository.save(edu);
                    System.out.println("Education id : " + edu.getId());
                    edu.setId(edu.getId());
                    educationList.add(edu);
                }
            }

            List<SocialactivitiesDTO> listAct = editedCv.getSocialactivitiesById();
            List<Socialactivities> socialactivitiesList = new ArrayList<>();
            socialactivitiesList = socialactivityRepository.findAllSocialactivityByCVID(editCv.getId());
            socialactivityRepository.delete(socialactivitiesList);
            socialactivitiesList.clear();
            if (listAct != null) {
                for (SocialactivitiesDTO socialactivitiesDTO : listAct) {
                    Socialactivities soc = modelMapper.map(socialactivitiesDTO, Socialactivities.class);
                    soc.setCvid(CVID);
                    soc.setCvByCvid(cv);
                    soc = socialactivityRepository.save(soc);
                    System.out.println("Socialactivities id : " + soc.getId());
                    soc.setId(soc.getId());
                    socialactivitiesList.add(soc);
                }
            }

            List<WorkexperienceDTO> listWor = editedCv.getWorkexperiencesById();
            List<Workexperience> workexperienceList = new ArrayList<>();
            workexperienceList = workexperienceRepository.findByCVID(editCv.getId());
            workexperienceRepository.delete(workexperienceList);
            workexperienceList.clear();
            if (listWor != null) {
                for (WorkexperienceDTO workexperienceDTO : listWor) {
                    Workexperience wor = modelMapper.map(workexperienceDTO, Workexperience.class);
                    wor.setCvid(CVID);
                    wor.setCvByCvid(cv);
                    wor = workexperienceRepository.save(wor);
                    System.out.println("Work Experience id : " + wor.getId());
                    wor.setId(wor.getId());
                    workexperienceList.add(wor);

                }
            }

            List<ProjectorproductworkedDTO> listPro = editedCv.getProjectorproductworkedsById();
            List<Projectorproductworked> projectorproductworkedList = new ArrayList<>();
            projectorproductworkedList = projectorproductworkedRepository.findByCVID(editCv.getId());
            projectorproductworkedRepository.delete(projectorproductworkedList);
            projectorproductworkedList.clear();
            if (listPro != null) {
                for (ProjectorproductworkedDTO projectorproductworkedDTO : listPro) {
                    Projectorproductworked pro = modelMapper.map(projectorproductworkedDTO, Projectorproductworked.class);
                    pro.setCvid(CVID);
                    pro.setCvByCvid(cv);
                    pro = projectorproductworkedRepository.save(pro);
                    System.out.println("ProjectWorked id : " + pro.getId());
                    pro.setId(pro.getId());
                    projectorproductworkedList.add(pro);
                }
            }
            editCv.setCertificationsById(certificationList);
            editCv.setSocialactivitiesById(socialactivitiesList);
            editCv.setEducationsById(educationList);
            editCv.setProjectorproductworkedsById(projectorproductworkedList);
            editCv.setWorkexperiencesById(workexperienceList);
            addSkill(editedCv, cv);
            List<Skillincv> listNewSkill = skillincvRepository.findSkillincvsByCvid(editedCv.getId());
            editCv.setSkillincvsById(listNewSkill);

            Cv saveCv = cvRepository.save(editCv);
            return new RestResponse(true, "Chỉnh sửa thành công!!!", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Có lỗi xãy ra. Vui lòng thử lại!!!", null);
    }

    @Override
    public RestResponse delete(int id) {
        Cv cv = cvRepository.findOne(id);
        if (cv == null)
            return new RestResponse(false, "Không tìm thấy CV có Id: " + id, null);
        cv.setStatus("2");
        cv.setIsActive(0);
        cvRepository.save(cv);
        return new RestResponse(true, "Đã xoá CV thành công!!!", null);
    }

    @Override
    public RestResponse getlistCvByUserId(int id) {
        Users user = usersRepository.findOne(id);
        if (user == null)
            return new RestResponse(false, "Không tìm thấy người dùng có ID là: " + id, null);
        List<Cv> list = cvRepository.findByUserIdAndStatus(id, "1");
        if (list == null)
            return new RestResponse(false, "Bạn chưa tạo CV!!!", null);
        return new RestResponse(true, "", list);
    }

    @Override
    public List<CVMobileDTO> getListCvByUserIdMobile(int id) {
        List<CVMobileDTO> listMobileCv = new ArrayList<>();
        List<Cv> listCv = cvRepository.findByUserIdAndStatus(id, "1");
        if (listCv != null) {
            for (Cv cv : listCv) {
                CVMobileDTO cvMobile = new CVMobileDTO();
                cvMobile.setId(cv.getId());
                cvMobile.setUserId(cv.getUserId());
                cvMobile.setTitle(cv.getTitle());
                cvMobile.setFullName(cv.getFirstName() + " " + cv.getLastName());
                cvMobile.setCityName(cv.getCityByCityId().getFullName());
                cvMobile.setIndustryName(cv.getIndustryByIndustryId().getName());
                cvMobile.setIsActive(cv.getIsActive());
                listMobileCv.add(cvMobile);
            }
            return listMobileCv;
        }
        return null;
    }

    @Override
    public RestResponse checkActive(int id) {
        List<Cv> list1 = cvRepository.findByUserId(id);
        if (list1 == null)
            return new RestResponse(true, "Bạn chưa tạo CV nào!!!", null);
        List<Cv> list = cvRepository.checkActive(id, 1);
        if (list == null)
            return new RestResponse(true, "Bạn chưa chọn CV nào là Cv chính của mình. Hãy đặt CV chính để không bỏ lỡ cơ hội tìm việc nhé!!!", null);
        return null;
    }

    @Override
    public RestResponse setMainCv(int id) {
        Cv cv = cvRepository.findOne(id);
        if (cv == null)
            return new RestResponse(false, "Có lỗi!!!!", null);
        List<Cv> list = cvRepository.findByUserId(cv.getUserId());
        for (Cv cv1 : list) {
            cv1.setIsActive(0);
            cvRepository.save(cv1);
        }
        cv.setIsActive(1);
        cvRepository.save(cv);
        return new RestResponse(true, "Cập nhật thành công!!!", null);
    }

    @Override
    public Page<Cv> searchCv(String skillstring, String city, String industry, Pageable pageable) {
        Page<Cv> listCv;
        System.out.println(skillstring);
        try {
            if (skillstring.equals("")) {
                listCv = cvRepository.searchWithoutSkill(pageable, city, industry);
                return listCv;
            } else {
                List<Integer> list = new ArrayList<>();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(skillstring);
                while (matcher.find()) {
                    list.add(Integer.parseInt(matcher.group()));
                }
                String skillName = "";
                int i = 0;
                for (Integer integer : list) {
                    Skillmaster skillmaster = skillmasterRepository.findOne(integer);
                    if (i < list.size() - 1) {
                        skillName = skillName + skillmaster.getSkillName() + ",";
                        i++;
                    } else {
                        skillName = skillName + skillmaster.getSkillName();
                    }
                }
                if (skillName.isEmpty()) {
                    listCv = cvRepository.searchWithoutSkill(pageable, city, industry);
                    return listCv;
                } else {
                    System.out.println(skillName);
                    System.out.println(city);
                    System.out.println(industry);
                    listCv = cvRepository.searchCv(skillName, pageable, city, industry);
                    return listCv;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Cv> suggest(int JobId, Pageable pageable) {
        Job job = jobRepository.findOne(JobId);
        if (job == null)
            return null;
        String cityName = job.getCityByCityId().getFullName();
        String industryName = job.getIndustryByIndustryId().getName();
        List<Skill> listSkill = new ArrayList<>();
        for (Skillneedforjob skillneedforjob : job.getSkillneedforjobsById()) {
            listSkill.add(skillneedforjob.getSkillBySkillId());
        }
        List<Skillmaster> listSkillMaster = new ArrayList<>();
        for (Skill skill : listSkill) {
            listSkillMaster.add(skill.getSkillmasterBySkillMasterId());
        }
        String listSkillName = "";
        int i = 0;
        for (Skillmaster skillmaster : listSkillMaster) {
            if (i < listSkillMaster.size() - 1) {
                listSkillName = listSkillName + skillmaster.getSkillName() + ",";
                i++;
            } else {
                listSkillName = listSkillName + skillmaster.getSkillName();
            }
        }
        try {
            // get ListCv liked by this Employer
            List<Userslikecv> listUserslikecv = userlikecvRepository.findUserslikecvsByUserId(job.getUserId());
            List<Cv> listLikedCv = new ArrayList<>();
            if (listUserslikecv != null) {
                for (Userslikecv userslikecv : listUserslikecv) {
                    listLikedCv.add(userslikecv.getCvByCvid());
                }
            }
            Page<Cv> pageCv = cvRepository.searchListCv(listSkillName,pageable, cityName, industryName);
//            listCv.removeAll(listLikedCv);

            return pageCv;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Cv> listCvConfirmed(int EmployerId, Pageable pageable) {
        try {
            List<Cv> listCv = getByJobIdAndStatus(EmployerId, "2");

            Page<Cv> pageCv = new PageImpl<>(listCv, pageable, listCv.size());
            return pageCv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Cv> listCvDenied(int EmployerId, Pageable pageable) {
        try {
            List<Cv> listCv = getByJobIdAndStatus(EmployerId, "3");
            Page<Cv> pageCv = new PageImpl<>(listCv, pageable, listCv.size());
            return pageCv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean check(int JobSeekerId) {
        int count = cvRepository.countCvsByUserIdAndStatus(JobSeekerId, "1");
        if (count >= 5)
            return false;
        return true;
    }

    private boolean checkIsActive(int userId) {
        List<Cv> list = cvRepository.checkIsActive(userId, 1);
        if (list.isEmpty())
            return true;
        return false;
    }

    private List<Cv> getByJobIdAndStatus(int EmployerId, String status) {
        List<Job> listJob = jobRepository.findJobsByStatusAndUserId("approved", EmployerId);
        List<Cv> listCv = new ArrayList<>();
        if (listJob.size() == 0) return listCv;
        for (Job job : listJob) {
            List<Apply> listApply = applyRepository.findAppliesByJobIdAndStatus(job.getId(), status);
            if (listApply != null) {
                for (Apply apply : listApply) {
                    listCv.add(apply.getCvByCvid());
                }
            }
        }
        return listCv;
    }

    private void addSkill(CVDTO newCV, Cv saveCv) {
        try {
            List<ListSkillDTO> listSkill = newCV.getSkillincvsById();
            if (listSkill != null) {
                for (ListSkillDTO listSkillDTO : listSkill) {
                    Skill skill = skillRepository.findSkillbySkillLevel(listSkillDTO.getSkillLevel(), listSkillDTO.getSkillMasterId());
                    if (skill != null) {
                        Skillincv skillInCv = new Skillincv();
                        skillInCv.setCvid(saveCv.getId());
                        skillInCv.setCvByCvid(saveCv);
                        skillInCv.setSkillId(skill.getId());
                        skillInCv.setSkillBySkillId(skill);
                        skillincvRepository.save(skillInCv);
                    } else {
                        Skillmaster skillMaster = skillmasterRepository.findOne(listSkillDTO.getSkillMasterId());
                        Skill newSkill = new Skill();
                        newSkill.setSkillLevel(listSkillDTO.getSkillLevel());
                        newSkill.setSkillMasterId(listSkillDTO.getSkillMasterId());
                        newSkill.setSkillmasterBySkillMasterId(skillMaster);
                        Skill skillSaved = skillRepository.saveAndFlush(newSkill);

                        Skillincv skillincv = new Skillincv();
                        skillincv.setCvid(saveCv.getId());
                        skillincv.setCvByCvid(saveCv);
                        skillincv.setSkillId(skillSaved.getId());
                        skillincv.setSkillBySkillId(skillSaved);
                        skillincvRepository.save(skillincv);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

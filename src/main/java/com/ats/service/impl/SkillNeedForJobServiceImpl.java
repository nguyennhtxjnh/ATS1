package com.ats.service.impl;

import com.ats.entity.Job;
import com.ats.entity.Skill;
import com.ats.entity.Skillneedforjob;
import com.ats.repository.SkillNeedForJobRepository;
import com.ats.service.SkillNeedForJobService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillNeedForJobServiceImpl implements SkillNeedForJobService {

    @Autowired
    SkillNeedForJobRepository skillNeedForJobRepository;

    private static final Logger LOGGER = LogManager.getLogger(SkillNeedForJobServiceImpl.class);

    @Override
    public boolean addSkillForJob(List<Integer> listSkillId, int jobId) {
        boolean result = false;
        int skillResult = -1;
        LOGGER.info("Begin addSkillForJob in SkillNeedForJob Service with JobID {}", listSkillId.size());
        try {
            for (int i = 0; i < listSkillId.size(); i++) {
                Skillneedforjob skillneedforjob = new Skillneedforjob();
                skillneedforjob.setJobId(jobId);
                skillneedforjob.setSkillId(listSkillId.get(i));
                skillResult = checkSkillNeedForJob(skillneedforjob);
                if (skillResult == -1) {
                    Skill skill = new Skill();
                    skill.setId(listSkillId.get(i));

                    Job job = new Job();
                    job.setId(jobId);

                    skillneedforjob.setSkillBySkillId(skill);
                    skillneedforjob.setJobByJobId(job);

                    skillNeedForJobRepository.save(skillneedforjob);

                }
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End addSkillForJob in SkillNeedForJob Service with JobID {}", jobId);
        return result;
    }

    @Override
    public int checkSkillNeedForJob(Skillneedforjob entity) {
        LOGGER.info("Begin checkSkillNeedForJob in SkillNeedForJob Service with skill master with skill level {}",
                entity.getJobId() + "-" + entity.getSkillId());
        try {
            Skillneedforjob skillResult = skillNeedForJobRepository.findSkillneedforjob(entity.getJobId(), entity.getSkillId());
            LOGGER.info("End checkSkillNeedForJob in SkillNeedForJob Service with skill master with skill level {}",
                    entity.getJobId() + "-" + entity.getSkillId());
            if (skillResult != null) {
                return skillResult.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateSkillForJob(List<Integer> listSkillId, int jobId) {
        boolean result = false;
        int skillResult = -1;
        LOGGER.info("Begin addSkillForJob in SkillNeedForJob Service with JobID {}", listSkillId.size());
        try {
            List<Skillneedforjob> skillneedforjobList = skillNeedForJobRepository.getAllByJobId(jobId);
            for (int i = 0; i < skillneedforjobList.size(); i++) {
                skillNeedForJobRepository.delete(skillneedforjobList.get(i).getId());
            }
            for (int i = 0; i < listSkillId.size(); i++) {
                Skillneedforjob skillneedforjob = new Skillneedforjob();
                skillneedforjob.setJobId(jobId);
                skillneedforjob.setSkillId(listSkillId.get(i));
                skillResult = checkSkillNeedForJob(skillneedforjob);
                if (skillResult == -1) {
                    Skill skill = new Skill();
                    skill.setId(listSkillId.get(i));

                    Job job = new Job();
                    job.setId(jobId);

                    skillneedforjob.setSkillBySkillId(skill);
                    skillneedforjob.setJobByJobId(job);

                    skillNeedForJobRepository.save(skillneedforjob);

                }
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End addSkillForJob in SkillNeedForJob Service with JobID {}", jobId);
        return result;
    }


}

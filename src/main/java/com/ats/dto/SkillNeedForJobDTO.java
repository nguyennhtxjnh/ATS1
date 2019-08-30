package com.ats.dto;


import lombok.Data;

import java.util.List;

@Data
public class SkillNeedForJobDTO {
    Integer id;
    Integer jobId;
    List<SkillDTO> listSkill;
}

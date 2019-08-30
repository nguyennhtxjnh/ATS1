package com.ats.ws;

import com.ats.dto.JobDTO;
import com.ats.service.ApplyService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyWS {
    @Autowired
    private ApplyService applyService;

    // create when jobseeker apply a job
    @RequestMapping(value = "/create/{CvId}/{JobId}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public RestResponse create(@PathVariable(name = "CvId") int CvId,
                               @PathVariable(name = "JobId") int JobId){
        return applyService.create(CvId, JobId);
    }

    // edit status - confirm
    @RequestMapping(value = "/confirm/{jobid}/{cvid}",method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse confirm(@PathVariable(name = "jobid") int jobid,
                                @PathVariable(name = "cvid") int cvid){
        return applyService.confirm(jobid, cvid);
    }

    // edit status - deny
    @RequestMapping(value = "/deny/{jobid}/{cvid}", method = RequestMethod.GET)
    @CrossOrigin("*")
    public RestResponse deny(@PathVariable(name = "jobid") int jobid,
                             @PathVariable(name = "cvid") int cvid){
        return applyService.deny(jobid, cvid);
    }

    // check
    @RequestMapping(value = "/check/{JobSeekerId}/{JobId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public boolean check(@PathVariable(name = "JobSeekerId") int jobseekerid,
                         @PathVariable(name = "JobId") int jobid){
        return applyService.check(jobseekerid, jobid);
    }

    // get list Apply cua mot job nao do
    @RequestMapping(value = "/list-applied/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse getListJobAppliedByJobId(@PathVariable(name = "id") int JobSeekerId){
        return applyService.listJob(JobSeekerId);
    }


    // get List Cv applied for one Job
    @GetMapping("/cv-applied/{jobid}")
    @CrossOrigin(origins = "*")
    public RestResponse getListCvAppliedForOneJob(@PathVariable(name = "jobid") int JobId,
                                              @PageableDefault(size = 5) Pageable pageable){
        return applyService.listCv(JobId, pageable);
    }

    // check Status appy
    @PostMapping("/checkstatus/{CvId}/{JobId}")
    @CrossOrigin("*")
    public RestResponse checkStatusApply(@PathVariable(name = "CvId") int CvId,
                                         @PathVariable(name = "JobId") int JobId){
        return applyService.checkStatusApply(CvId, JobId);
    }

    @GetMapping("/getAllAplly/{userId}")
    @CrossOrigin("*")
    public RestResponse getAllAplly(@PathVariable(name = "userId") int userId){
        return applyService.getAllApply(userId);
    }

    @GetMapping("/getAllApllyMobile/{userId}")
    @CrossOrigin("*")
    public int getAllApllyMobile(@PathVariable(name = "userId") int userId){
        return applyService.getAllApplyMobile(userId);
    }


    // get list job applied of JobSeekerId
    @GetMapping("/list-applied/mobile/{id}")
    @CrossOrigin(origins = "*")
    public List<JobDTO> getListJobAppliedMobile(@PathVariable int id){
        return applyService.listJobMobile(id);
    }
}

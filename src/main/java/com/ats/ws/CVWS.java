package com.ats.ws;

import com.ats.dto.CVDTO;
import com.ats.dto.CVMobileDTO;
import com.ats.entity.Cv;
import com.ats.service.CVService;
import com.ats.service.CountcvService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cv")
public class CVWS {
    @Autowired
    private CVService cvService;
    @Autowired
    private CountcvService countcvService;

    private static final Logger LOGGER = LogManager.getLogger(CVWS.class);

    // Get CV By CVID -- tested
    @RequestMapping(value = "/getOne/{CVID}/{EmployerID}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse getCV(@PathVariable(name = "CVID") Integer CVID,
                              @PathVariable(name = "EmployerID") Integer EmployerId){
        if (EmployerId == 0 ){
            return cvService.getCVByCVID(CVID);
        } else {
            countcvService.countWhenEmployerGetDetailOfCV(CVID, EmployerId);
            return cvService.getCVByCVID(CVID);
        }
    }


    // GET List CV cho User xem bang UserID - tested - co status = 1 (valid)
    @GetMapping("/get-list/{id}")
    @CrossOrigin("*")
    public RestResponse getListCvByUserId(@PathVariable(name = "id") int UserId){
        return cvService.getlistCvByUserId(UserId);
    }

    @GetMapping("/get-list/mobile/{id}")
    @CrossOrigin("*")
    public List<CVMobileDTO> getListCvByUserIdMobile(@PathVariable(name = "id") int UserId){
        return cvService.getListCvByUserIdMobile(UserId);
    }

    // Delete One CV
    @RequestMapping(value = "/deleteCV/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public RestResponse deleteACV(@PathVariable int id){
        return cvService.delete(id);
    }


    // Edit information's cv
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public RestResponse  editCv(@RequestBody CVDTO editedCv){
        return cvService.edit(editedCv);
    }

    // Create New Cv
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @CrossOrigin("*")
    @ResponseBody
    public RestResponse create(@Valid @RequestBody  CVDTO newCv,
                               BindingResult result){
        if (result.hasErrors())
            return new RestResponse(false ,"ERROR: " + result.toString(), null);
        return cvService.create(newCv);
    }

    // Check xem no da co CV nao Active chua
    @RequestMapping(value = "/check-acrive/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public RestResponse checkActive(@PathVariable int id){
        return cvService.checkActive(id);
    }

    // Set CV là cv chính
    @RequestMapping(value = "/set-main-cv/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse setMainCV(@PathVariable int id){
        return cvService.setMainCv(id);
    }

    // search Cv
    @GetMapping("/search")
    @CrossOrigin(origins = "*")
    public Page<Cv> searchCv(@RequestParam(name = "listskill") String listSkill,
                             @RequestParam(name = "city") String cityName,
                             @RequestParam(name = "industry") String industryName,
                             @PageableDefault(size = 5) Pageable pageable){
        return cvService.searchCv(listSkill, cityName, industryName, pageable);
    }

    // suggest cv for 1 Job
    @GetMapping("/suggest/{JobId}")
    @CrossOrigin(origins = "*")
    public Page<Cv> suggest(@PathVariable(name = "JobId") int JobId,
                            @PageableDefault(size = 5) Pageable pageable){
        return cvService.suggest(JobId, pageable);
    }

    // get all cv that confirmed by employer
    @GetMapping("/list-comfirmed/{EmployerId}")
    @CrossOrigin(origins = "*")
    public Page<Cv> listCvConfirmed(@PathVariable(name = "EmployerId") int EmployerId,
                                    @PageableDefault(size = 5) Pageable pageable){
        return cvService.listCvConfirmed(EmployerId, pageable);
    }

    // get all cv thay denied by employer
    @GetMapping("/list-denied/{EmployerId}")
    @CrossOrigin(origins = "*")
    public Page<Cv> listCvDenied(@PathVariable(name = "EmployerId") int EmployerId,
                                 @PageableDefault(size = 5) Pageable pageable){
        return cvService.listCvDenied(EmployerId, pageable);
    }

    // check when creata that user can't have over 5 Cv.
    @GetMapping("/check/{JobSeekerId}")
    @CrossOrigin(origins = "*")
    public boolean check(@PathVariable(name = "JobSeekerId") int JobSeekerId){
        return cvService.check(JobSeekerId);
    }
}

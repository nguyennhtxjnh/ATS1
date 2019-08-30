package com.ats.ws;

import com.ats.service.UserlikecvService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userlifecv")
public class UserlikecvWS {
    @Autowired
    private UserlikecvService userlikecvService;

    // Create
    @PostMapping("/create/{EmployerId}/{CvId}")
    @CrossOrigin(origins = "*")
    public RestResponse create(@PathVariable(name = "EmployerId") int EmployerId,
                               @PathVariable(name = "CvId") int CvId){
        return userlikecvService.create(EmployerId, CvId);
    }

    // check
    @GetMapping("/check/{EmployerId}/{CvId}")
    @CrossOrigin(origins = "*")
    public boolean check(@PathVariable(name = "EmployerId") int EmployerId,
                         @PathVariable(name = "CvId") int CvId){
        return userlikecvService.check(EmployerId,CvId);
    }

    // List all cv that Employer used to like
    @GetMapping("/list/{EmployerId}")
    @CrossOrigin(origins = "*")
    public RestResponse listCv(@PathVariable(name = "EmployerId") int EmployerId,
                               @PageableDefault Pageable pageable){
        return userlikecvService.listCv(EmployerId,pageable);
    }

    //un-unlike
    @PostMapping("/unlike/{EmployerId}/{CvId}")
    @CrossOrigin("*")
    public RestResponse unLike(@PathVariable(name = "EmployerId") int EmployerId,
                               @PathVariable(name = "CvId") int CvId){
        return userlikecvService.unCheck(EmployerId,CvId);
    }
}
package com.ats.ws;

import com.ats.dto.CountjobDTO;
import com.ats.entity.Countjob;
import com.ats.repository.CountjobRepository;
import com.ats.service.CountjobService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countjob")
public class CountjobWS {
    @Autowired
    private CountjobService countjobService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public int countJob(@PathVariable(name = "id") int id){
        return countjobService.countAccessTimes(id);
    }


    // Count in one months
    @GetMapping("count-month/{id}")
    @CrossOrigin("*")
    public RestResponse countMonth(@PathVariable Integer id){
        return null;
    }

    //Test get
    @Autowired
    private CountjobRepository countjobRepository;
    @RequestMapping(value = "/testGet", method = RequestMethod.GET)
    public List<Countjob> testGet(){
        return countjobRepository.findAll();
    }

    //Test Post
    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public void testPost(@RequestBody CountjobDTO newCountjobDTO){
        countjobService.countWhenEmployerGetDetailOfJob(newCountjobDTO.getJobId(), newCountjobDTO.getUserId());
    }
}

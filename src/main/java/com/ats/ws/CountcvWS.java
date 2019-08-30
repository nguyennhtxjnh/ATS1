package com.ats.ws;

import com.ats.dto.CountcvDTO;
import com.ats.entity.Countcv;
import com.ats.repository.CountcvRepository;
import com.ats.service.CountcvService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countcv")
public class CountcvWS {
    @Autowired
    private CountcvService countcvService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public int countCv(@PathVariable(name = "id") Integer id){
        return countcvService.countAccessTimes(id);
    }

    // Count in one months
    @GetMapping("/count-month/{id}")
    @CrossOrigin("*")
    public RestResponse countMonth(@PathVariable int id){
        return null;
    }

    // Test get
    @Autowired
    private CountcvRepository countcvRepository;
    @RequestMapping(value = "/testGet", method = RequestMethod.GET)
    public List<Countcv> testGet(){
        return countcvRepository.findAll();
    }

    // Test Post
    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public void testPost(@RequestBody CountcvDTO newCv){
        countcvService.countWhenEmployerGetDetailOfCV(newCv.getCvid(), newCv.getUserId());
    }
}

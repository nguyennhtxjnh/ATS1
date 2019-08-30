package com.ats.ws;

import com.ats.service.JoblevelService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/joblevel")
public class JoblevelWS  {
    @Autowired
    JoblevelService joblevelService;

    private static final Logger LOGGER = LogManager.getLogger(JoblevelWS.class);

    @CrossOrigin(origins = "http://localhost:8090")
    @GetMapping(value = "/")
    public RestResponse getJobLevel() {
        LOGGER.info("Begin getJobLevel in JoblevelWS ");
        List result ;
        try {
            result = joblevelService.getAllJobLevel();
                return new RestResponse(true, "Get All Job Level Successfull", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobLevel in JoblevelWS");
        return new RestResponse(false, "Fail To getJobLevel ", null);

    }
}

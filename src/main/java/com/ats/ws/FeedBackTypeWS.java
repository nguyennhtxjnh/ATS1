package com.ats.ws;

import com.ats.entity.Feedbacktype;
import com.ats.service.FeedBackTypeService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacktype")
public class FeedBackTypeWS {

    @Autowired
    FeedBackTypeService feedBackTypeService;
    private static final Logger LOGGER = LogManager.getLogger(FeedBackWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAllFeedBackType")
    public RestResponse getAllFeedBack(@PageableDefault Pageable pageable) {
        LOGGER.info("Begin getAllFeedBack in JobWS with Job title : {}");
        List<Feedbacktype> feedbacktypeList;
        try {
            feedbacktypeList = feedBackTypeService.getAllFeedBackType();
            if (feedbacktypeList.size() > 0) {
                return new RestResponse(true, "getAllFeedBackType Successfull", feedbacktypeList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "getAllFeedBackType Fail ", null);
    }

}

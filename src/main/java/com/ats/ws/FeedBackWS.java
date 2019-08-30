package com.ats.ws;

import com.ats.dto.FeedBackDTO;
import com.ats.dto.FeedBackDTO2;
import com.ats.entity.Feedback;
import com.ats.service.FeedBackService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedBackWS {

    @Autowired
    FeedBackService feedBackService;

    private static final Logger LOGGER = LogManager.getLogger(FeedBackWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/create")
    public RestResponse createFeedBack(@RequestBody FeedBackDTO dto) {
        LOGGER.info("Begin createFeedBack in FeedBackWS with Job title : {}");
        int result = 0;
        try {
            dto.setCreatedDate(new Date());
            dto.setIsReply(0);
            result = feedBackService.createFeedBack(dto);
            if (result > 0) {
                return new RestResponse(true, "Create New FeedBack Successfull", result);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New FeedBack ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAllFeedBack")
    public RestResponse getAllFeedBack(@PageableDefault Pageable pageable, @RequestParam("search") String search) {
        LOGGER.info("Begin getAllFeedBack in FeedBackWS with Job title : {}");
        Page<FeedBackDTO2> feedbackPage = null;
        try {
            feedbackPage = feedBackService.getAllFeedBack(pageable, search);
            if (feedbackPage != null) {
                return new RestResponse(true, "getAllFeedBack Successfull", feedbackPage);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "getAllFeedBack Fail ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/checkFeedBack")
    public RestResponse checkFeedBack(@RequestParam("userId") int userId) {
        LOGGER.info("Begin checkFeedBack in FeedBackWS with Job title : {}");
        List<Integer> feedbackPage = null;
        try {
            feedbackPage = feedBackService.checkFeedBack(userId);
            if (feedbackPage != null) {
                return new RestResponse(true, "checkFeedBack Successfull", feedbackPage);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "checkFeedBack Fail ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/checkIsReply")
    public RestResponse checkIsReply(@RequestParam("jobId") int jobId, @RequestParam("userId") int userId) {
        LOGGER.info("Begin checkFeedBack in FeedBackWS with Job title : {}");
        int result = 0;
        try {
            result = feedBackService.checkIsReply(jobId, userId);
            if (result > 0) {
                return new RestResponse(true, "checkFeedBack Successfull", null);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "checkFeedBack Fail ", null);
    }

}

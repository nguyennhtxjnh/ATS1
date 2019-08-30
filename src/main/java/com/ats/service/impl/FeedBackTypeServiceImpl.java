package com.ats.service.impl;

import com.ats.entity.Feedbacktype;
import com.ats.repository.FeedBackTypeRepository;
import com.ats.service.FeedBackTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackTypeServiceImpl implements FeedBackTypeService {
    @Autowired
    FeedBackTypeRepository feedBackTypeRepository;

    private static final Logger LOGGER = LogManager.getLogger(FeedBackTypeServiceImpl.class);
    @Override
    public List<Feedbacktype> getAllFeedBackType() {

        LOGGER.info("Begin getAllFeedBackType in Feedback Service with job name : {}");
        List<Feedbacktype> feedbacktypeList = null;
        try {
            feedbacktypeList = feedBackTypeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllFeedBackType in Feedback Service with job name : {}");
        return feedbacktypeList;
    }
}

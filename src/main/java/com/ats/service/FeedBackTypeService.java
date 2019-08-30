package com.ats.service;

import com.ats.entity.Feedbacktype;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedBackTypeService {
    List<Feedbacktype> getAllFeedBackType();
}

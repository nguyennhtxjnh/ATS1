package com.ats.service;

import com.ats.dto.FeedBackDTO;
import com.ats.dto.FeedBackDTO2;
import com.ats.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedBackService {

    int createFeedBack(FeedBackDTO dto);

    Page<Feedback> getFeedBackByJobId(int jobid);

    Page<FeedBackDTO2> getAllFeedBack(Pageable pageable, String search);

    List<Integer> checkFeedBack(int userId);

    int checkIsReply(int userId, int jobId);
}

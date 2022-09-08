package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.dao.FeedbackMapper;
import com.beaconfire.quizsystem.entity.mentity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    public int saveFeedback(Feedback feedback){
        return feedbackMapper.insertSelective(feedback);
    }
}

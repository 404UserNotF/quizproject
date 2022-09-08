package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.entity.Questionnaire;
import com.beaconfire.quizsystem.entity.ResponseMsg;
import com.beaconfire.quizsystem.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TimeController {
    private final long TEST_DURATION = Constants.TEST_DURATION;
    @RequestMapping(path="/time/verify")
    @ResponseBody
    public ResponseMsg verifyTime(@RequestParam long curTime, HttpServletRequest request){
        HttpSession session = request.getSession();
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("qa");
        long startTime = questionnaire.getStartTime();
        // 1 sec = 1000 ms
        if(curTime - startTime >= TEST_DURATION){
            ResponseMsg responseMsg = new ResponseMsg();
            responseMsg.setMessage("timeout");
            responseMsg.setUrl("/result/resultCount");
            return responseMsg;
        }else{
            ResponseMsg responseMsg = new ResponseMsg();
            responseMsg.setMessage("valid");
            responseMsg.setTimeLeft(startTime + TEST_DURATION - curTime);
            return responseMsg;
        }
    }
}

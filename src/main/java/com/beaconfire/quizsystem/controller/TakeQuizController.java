package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.entity.hentity.QuizEntity;
import com.beaconfire.quizsystem.entity.hentity.QuizResultEntity;
import com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity;
import com.beaconfire.quizsystem.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class TakeQuizController {
    @Autowired
    private QuizResultService quizResultService;

    @RequestMapping(path="/takeQuizRecord/toRecordDetail")
    public String toTakeQuizDetail(@RequestParam Integer takeQuizId,
                                   Model model){
        List<QuizResultEntity> quizResultEntities = quizResultService.getQuizResultByTakeQuizId(takeQuizId);
        model.addAttribute("qrs", quizResultEntities);
        return "takeQuizDetail";
    }
}

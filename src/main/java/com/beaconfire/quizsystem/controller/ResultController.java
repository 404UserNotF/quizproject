package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.entity.*;
import com.beaconfire.quizsystem.entity.hentity.QuizEntity;
import com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity;
import com.beaconfire.quizsystem.entity.hentity.UserEntity;
import com.beaconfire.quizsystem.entity.mentity.Quiz;
import com.beaconfire.quizsystem.entity.mentity.TakeQuiz;
import com.beaconfire.quizsystem.entity.mentity.User;
import com.beaconfire.quizsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ResultController {
    @Autowired
    private ResultService resultService;
    @Autowired
    private TakeQuizService takeQuizService;
    @Autowired
    private QuizResultService quizResultService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;

    @RequestMapping(path="/result/resultCount")
    public String resultCount(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Questionnaire questionnaire = (Questionnaire)session.getAttribute("qa");
        questionnaire.setEndTime(new Date().getTime());
        resultService.countTheResult(questionnaire);
        TakeQuizEntity takeQuizRecord = takeQuizService.insertTakeQuizResult(questionnaire);
        quizResultService.saveQuizResult(questionnaire, takeQuizRecord);
        UserEntity user = userService.findUserById(questionnaire.getUserId());
        String username = user.getUserName();
        QuizEntity quiz = quizService.selectQuizByPrimaryKey(questionnaire.getQuestionAndChoices().get(0).getQuestion().getQuizId());
        model.addAttribute("qa", questionnaire);
        model.addAttribute("username", username);
        model.addAttribute("quizname", quiz.getQuizName());
        model.addAttribute("startTime", new SimpleDateFormat("MM-dd hh:mm:ss").format(new Date(questionnaire.getStartTime())));
        model.addAttribute("endTime", new SimpleDateFormat("MM-dd hh:mm:ss").format(new Date(questionnaire.getEndTime())));
        return "result";
    }
}

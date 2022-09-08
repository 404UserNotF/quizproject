package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.entity.hentity.QuizEntity;
import com.beaconfire.quizsystem.entity.mentity.Feedback;
import com.beaconfire.quizsystem.entity.mentity.Quiz;
import com.beaconfire.quizsystem.service.FeedbackService;
import com.beaconfire.quizsystem.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FeedbackController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(path = "/feedback/toFeedback")
    public String toFeedback(Model model){
        List<QuizEntity> allQuiz = quizService.selectAll();
        model.addAttribute("allQuiz", allQuiz);
        return "feedback";
    }

    @RequestMapping(path = "/feedback/saveFeedback")
    public String saveFeedback(@RequestParam Integer quizId, @RequestParam Integer rating,
                             @RequestParam String review, HttpServletRequest request){
        HttpSession session = request.getSession();
        Feedback feedback = new Feedback();
        feedback.setQuizId(quizId);
        feedback.setRating(rating);
        feedback.setReview(review);
        feedback.setUserId((Integer)session.getAttribute("userId"));
        feedbackService.saveFeedback(feedback);
        return "redirect:/";
    }
}

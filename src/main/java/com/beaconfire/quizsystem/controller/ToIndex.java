package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.entity.hentity.QuizEntity;
import com.beaconfire.quizsystem.entity.mentity.Quiz;
import com.beaconfire.quizsystem.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ToIndex {
    @Autowired
    private QuizService quizService;
    @RequestMapping("/")
    public String toIndex(Model model){
        List<QuizEntity> quizs = quizService.selectAll();
        model.addAttribute("quizs", quizs);
        return "index";
    }
}

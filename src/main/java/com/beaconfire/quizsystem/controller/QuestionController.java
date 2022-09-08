package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import com.beaconfire.quizsystem.entity.Questionnaire;
import com.beaconfire.quizsystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(path="/question/startTest")
    public String startTest(@RequestParam int type,
                            Model model,
                            HttpServletRequest request){
        Questionnaire questionnaire = questionService.getQuestionnaireForTest(type);
        HttpSession session = request.getSession();
        questionnaire.setUserId((Integer)session.getAttribute("userId"));
        session.setAttribute("qa",questionnaire);

        model.addAttribute("qa", questionnaire);
        model.addAttribute("index", 0);

        return "question";
    }

    @RequestMapping(path="/question/answer")
    public String doAnswer(@RequestParam(value="index") int index,
                           @RequestParam(value="userChoices",required=false, defaultValue="")Integer[] userChoiceIds,
                           @RequestParam(value="action", required=false, defaultValue="jump") String action,
                           @RequestParam(value="toIndex", required=false) Integer toIndex,
                           HttpServletRequest request,
                           Model model){
        // userChoice is the choice id
        HttpSession session = request.getSession();
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("qa");
        // reset the question
        for(ChoiceEntity c : questionnaire.getQuestionAndChoices().get(index).getChoices()){
            if(c.getIsSelected() == 1){
                c.setIsSelected((byte) 0);
            }
        }

        Set<Integer> ids = new HashSet<Integer>();
        // record the user choice
        for(int id : userChoiceIds){
            ids.add(id);
        }
        for(ChoiceEntity c : questionnaire.getQuestionAndChoices().get(index).getChoices()){
            if(ids.contains(c.getChoiceId())){
                c.setIsSelected((byte)1);
            }
        }

        if(action.equals("finish")){
            return "redirect:/result/resultCount";
        }
        if(action.equals("jump")){
            model.addAttribute("qa", questionnaire);
            model.addAttribute("index", toIndex);
            return "question";
        }else if(action.equals("previous")){
            model.addAttribute("qa", questionnaire);
            model.addAttribute("index", index-1);
            return "question";
        }else{
            model.addAttribute("qa", questionnaire);
            model.addAttribute("index", index+1);
            return "question";
        }
    }
}

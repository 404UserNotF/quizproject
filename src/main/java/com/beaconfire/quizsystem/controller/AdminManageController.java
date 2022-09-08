package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.entity.ResponseMsg;
import com.beaconfire.quizsystem.entity.hentity.*;
import com.beaconfire.quizsystem.service.*;
import com.beaconfire.quizsystem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class AdminManageController {
    @Autowired
    private TakeQuizService takeQuizService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ChoiceService choiceService;
    /**
     * sortedBy
     *      1. by default, sort by startTime
     *      2. sort by username
     *      3. sort by category
     * quizId
     *      only display certain type of quiz, -1 meaning no specific quiz is selected
     * */
    @RequestMapping(path="/takeQuizRecord", method=RequestMethod.GET)
    @Cacheable(cacheNames = "pageInfo")
    public String takeQuizPageTo(@RequestParam Integer toPage,
                                 @RequestParam Integer limit,
                                 @RequestParam(required = false) Integer sortedBy,
                                 @RequestParam(required = false) Integer quizId,
                                 Model model){

        Long totalCount = takeQuizService.findCountOfRecordQuizIdSeletive(quizId);
        List<QuizEntity> quizEntities = quizService.selectAll();
        int from = (toPage - 1) * limit;
        List<TakeQuizEntity> takeQuizEntities = takeQuizService.findRecordFromTo(from, limit, sortedBy, quizId);
        model.addAttribute("records", takeQuizEntities);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("curPage", toPage);
        model.addAttribute("from", from);
        model.addAttribute("limit", limit);
        model.addAttribute("sortedBy", sortedBy);
        model.addAttribute("qz", quizEntities);
        model.addAttribute("picked", quizId);

        return "adminHomePage";
    }

    @RequestMapping(path="/admin/getAllUser", method=RequestMethod.GET)
    public String getAllUserInfo(@RequestParam Integer toPage,
                                 @RequestParam Integer limit,
                                 Model model){
        System.out.println(toPage);
        System.out.println(limit);
        int from = (toPage - 1) * limit;
        List<UserEntity> users = userService.getUserFromTo(from, limit);
        long total = userService.getAllUserCount();
        model.addAttribute("users", users);
        model.addAttribute("curPage", toPage);
        model.addAttribute("limit", limit);
        model.addAttribute("totalCount",total);
        model.addAttribute("from", from);
        return "userinfo";
    }

    @RequestMapping(path="/admin/changeUserStatusTo", method=RequestMethod.POST)
    @ResponseBody
    public ResponseMsg changeUserStatusTo(@RequestParam Integer userId, @RequestParam String toStatus){
        System.out.println(userId);
        System.out.println(toStatus);
        userService.changeUserStatusTo(userId, toStatus);
        ResponseMsg msg = new ResponseMsg();
        if(toStatus.equals("active")){
            msg.setMessage("suspend");
        }else{
            msg.setMessage("active");
        }
        return msg;
    }

    @RequestMapping(path="/admin/question/toAddQuestionPage")
    public String toAddQuestionPage(Model model, HttpServletRequest request){
        List<QuizEntity> quizEntities = quizService.selectAll();
        HttpSession session = request.getSession();
        session.setAttribute("newQuestion", new QuestionEntity());
        model.addAttribute("qzs", quizEntities);
        return "addQuestion";
    }

    @RequestMapping(path="/admin/question/addQuestion", method=RequestMethod.POST)
    public String addQuestionContentAndToAddChoice(@RequestParam Integer quizId,
                                                   @RequestParam String choiceType,
                                                   @RequestParam String questionContent,
                                                   Model model,
                                                   HttpServletRequest request){
        HttpSession session = request.getSession();
        QuestionEntity newQuestion = (QuestionEntity) session.getAttribute("newQuestion");
        newQuestion.setQuestionContent(questionContent);
        newQuestion.setQuizId(quizId);
        newQuestion.setQuestionStatus(choiceType);
        model.addAttribute("questionContent", newQuestion.getQuestionContent());
        return "addChoice";
    }

    @RequestMapping(path="/admin/question/addChoice", method=RequestMethod.POST)
    @ResponseBody
    public ChoiceEntity addChoice(@RequestParam String optionContent,
                                  @RequestParam Byte isCorrect,
                                  HttpServletRequest request){
        HttpSession session = request.getSession();
        QuestionEntity newQuestion = (QuestionEntity) session.getAttribute("newQuestion");
        ChoiceEntity newChoice = new ChoiceEntity();
        newChoice.setOptionContent(optionContent);
        newChoice.setIsCorrect(isCorrect);
        newQuestion.getChoiceEntities().add(newChoice);
        return newChoice;
    }

    @RequestMapping(path="/admin/question/saveQuestion")
    public String saveQuestion(HttpServletRequest request){
        QuestionEntity newQuestion = (QuestionEntity) request.getSession().getAttribute("newQuestion");
        List<ChoiceEntity> choiceEntities = newQuestion.getChoiceEntities();
        for(ChoiceEntity c : choiceEntities){
            c.setQuestion(newQuestion);
        }
        choiceService.saveQuestionAndItChoice(choiceEntities);
        return "redirect:/admin/question/toAddQuestionPage";
    }

}

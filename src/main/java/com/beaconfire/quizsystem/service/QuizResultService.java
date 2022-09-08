package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.dao.QuizResultDAO;
import com.beaconfire.quizsystem.dao.QuizResultMapper;
import com.beaconfire.quizsystem.entity.*;
import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import com.beaconfire.quizsystem.entity.hentity.QuizResultEntity;
import com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import com.beaconfire.quizsystem.entity.QuestionAndChoice;
import com.beaconfire.quizsystem.entity.mentity.QuizResult;
import com.beaconfire.quizsystem.entity.mentity.TakeQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizResultService {
    @Autowired
    private QuizResultMapper quizResultMapper;
    @Autowired
    private QuizResultDAO quizResultDAO;

    /*public int saveQuizResult(Questionnaire questionnaire, TakeQuiz record){
        int insertCounter = 0;
        for(QuestionAndChoice qc : questionnaire.getQuestionAndChoices()){
            QuizResult newQuizResult = new QuizResult();
            newQuizResult.setQuestionId(qc.getQuestion().getQuestionId());
            newQuizResult.setTakeQuizId(record.getTakeQuizId());
            StringBuffer sb = new StringBuffer();
            for(ChoiceEntity c : qc.getChoices()){
                if(c.getIsSelected() == 1){
                    sb.append(c.getChoiceId()+" ");
                }
            }
            String userChoiceIds = sb.toString();
            userChoiceIds = userChoiceIds.trim();
            newQuizResult.setUserChoice(userChoiceIds);
            insertCounter += quizResultMapper.insertSelective(newQuizResult);
        }
        return insertCounter;
    }*/
    public int saveQuizResult(Questionnaire questionnaire, TakeQuizEntity record){
        int insertCounter = 0;
        for(QuestionAndChoice qc : questionnaire.getQuestionAndChoices()){
            QuizResult newQuizResult = new QuizResult();
            newQuizResult.setQuestionId(qc.getQuestion().getQuestionId());
            newQuizResult.setTakeQuizId(record.getTakeQuizId());
            StringBuffer sb = new StringBuffer();
            for(ChoiceEntity c : qc.getChoices()){
                if(c.getIsSelected() == 1){
                    sb.append(c.getChoiceId()+" ");
                }
            }
            String userChoiceIds = sb.toString();
            userChoiceIds = userChoiceIds.trim();
            newQuizResult.setUserChoice(userChoiceIds);
            insertCounter += quizResultMapper.insertSelective(newQuizResult);
        }
        return insertCounter;
    }

    public List<QuizResultEntity> getQuizResultByTakeQuizId(Integer takeQuizId){
        List<QuizResultEntity> quizResultEntities = quizResultDAO.getResultByTakeQuizId(takeQuizId);
        for(QuizResultEntity quizResult : quizResultEntities){
            String[] userChoicesArr = quizResult.getUserChoice().split(" ");
            // if user choice is empty
            if("".equals(userChoicesArr[0])){
                break;
            }
            Set<Integer> userChoices = new HashSet<>();
            for(String c : userChoicesArr){
                userChoices.add(Integer.parseInt(c));
            }
            for(ChoiceEntity ch : quizResult.getQuestion().getChoiceEntities()){
                if(userChoices.contains(ch.getChoiceId())){
                    ch.setIsSelected((byte)1);
                }
            }
        }
        return quizResultEntities;
    }
}

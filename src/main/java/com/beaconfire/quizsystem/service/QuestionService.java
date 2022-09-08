package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.dao.ChoiceMapper;
import com.beaconfire.quizsystem.dao.QuestionDAO;
import com.beaconfire.quizsystem.dao.QuestionMapper;
import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import com.beaconfire.quizsystem.entity.hentity.QuestionEntity;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import com.beaconfire.quizsystem.entity.mentity.Question;
import com.beaconfire.quizsystem.entity.QuestionAndChoice;
import com.beaconfire.quizsystem.entity.Questionnaire;
import com.beaconfire.quizsystem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ChoiceMapper choiceMapper;
    @Autowired
    private QuestionDAO questionDAO;

    /*public Questionnaire getQuestionnaireForTest(int quizId){
        List<Question> questionList = questionMapper.selectByQuizId(quizId, Constants.NUMBER_OF_QUESTION);
        Questionnaire questionnaire = new Questionnaire(new Date().getTime());
        // bind question and it's choices
        for(Question question : questionList){
            QuestionAndChoice questionAndChoice = new QuestionAndChoice();
            questionAndChoice.setQuestion(question);
            List<Choice> choices = choiceMapper.selectByQuestionId(question.getQuestionId());
            questionAndChoice.setChoices(choices);
            questionnaire.getQuestionAndChoices().add(questionAndChoice);
        }
        questionnaire.setDuration(Constants.TEST_DURATION);
        return questionnaire;
    }*/

    public Questionnaire getQuestionnaireForTest(int quizId){
        List<QuestionEntity> questionList = questionDAO.getRandomQuestions(quizId, Constants.NUMBER_OF_QUESTION);
        Questionnaire questionnaire = new Questionnaire(new Date().getTime());
        // bind question and it's choices
        for(QuestionEntity question : questionList){
            QuestionAndChoice questionAndChoice = new QuestionAndChoice();
            questionAndChoice.setQuestion(question);
            List<ChoiceEntity> choices = question.getChoiceEntities();
            Collections.shuffle(choices);
            questionAndChoice.setChoices(choices);
            questionnaire.getQuestionAndChoices().add(questionAndChoice);
        }
        questionnaire.setDuration(Constants.TEST_DURATION);
        return questionnaire;
    }

}

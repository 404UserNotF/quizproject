package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import com.beaconfire.quizsystem.entity.QuestionAndChoice;
import com.beaconfire.quizsystem.entity.Questionnaire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    public void countTheResult(Questionnaire questionnaire){
        List<QuestionAndChoice> qcList = questionnaire.getQuestionAndChoices();

        for(QuestionAndChoice qc : qcList){
            int numOfRightChoice = 0;
            int numOfUserRightChoice = 0;
            int numOfUserChoice = 0;
            for(ChoiceEntity c : qc.getChoices()){
                if(c.getIsSelected() == 1){
                    numOfUserChoice++;
                }
                if(c.getIsCorrect() == 1){
                    numOfRightChoice++;
                    if(c.getIsSelected() == 1){
                        numOfUserRightChoice++;
                    }
                }
            }
            if(numOfUserChoice == numOfRightChoice && numOfUserRightChoice == numOfRightChoice){
                questionnaire.correctCountIncrement();
                qc.setIsCorrect(1);
            }else{
                qc.setIsCorrect(0);
            }
        }
    }
}

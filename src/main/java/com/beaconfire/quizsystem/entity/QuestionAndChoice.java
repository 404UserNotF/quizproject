package com.beaconfire.quizsystem.entity;


import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import com.beaconfire.quizsystem.entity.hentity.QuestionEntity;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import com.beaconfire.quizsystem.entity.mentity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAndChoice {
    private QuestionEntity question;
    private List<ChoiceEntity> choices;
    /**
     * 0 is incorrect, 1 is correct
     * */
    private int isCorrect;

    public QuestionAndChoice(){
        choices = new ArrayList<>();
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public List<ChoiceEntity> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceEntity> choices) {
        this.choices = choices;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }
}

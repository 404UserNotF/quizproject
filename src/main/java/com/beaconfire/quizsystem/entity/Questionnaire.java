package com.beaconfire.quizsystem.entity;


import java.util.ArrayList;
import java.util.List;

public class Questionnaire {
    private Long startTime;
    private Long endTime;
    private List<QuestionAndChoice> questionAndChoices;
    private int correctCount;
    private int userId;
    private Long duration;
    private int questionLeft;

    public Questionnaire(Long startTime){
        this.startTime = startTime;
        this.questionAndChoices = new ArrayList<>();
        correctCount = 0;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<QuestionAndChoice> getQuestionAndChoices() {
        return questionAndChoices;
    }

    public void setQuestionAndChoices(List<QuestionAndChoice> questionAndChoices) {
        this.questionAndChoices = questionAndChoices;
    }

    public void correctCountIncrement(){
        correctCount++;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionLeft() {
        return questionLeft;
    }

    public void setQuestionLeft(int questionLeft) {
        this.questionLeft = questionLeft;
    }
}

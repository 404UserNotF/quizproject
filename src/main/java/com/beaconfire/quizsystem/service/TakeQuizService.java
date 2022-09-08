package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.dao.TakeQuizDAO;
import com.beaconfire.quizsystem.dao.TakeQuizMapper;
import com.beaconfire.quizsystem.entity.Questionnaire;
import com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity;
import com.beaconfire.quizsystem.entity.hentity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
public class TakeQuizService {
    @Autowired
    private TakeQuizMapper takeQuizMapper;
    @Autowired
    private TakeQuizDAO takeQuizDAO;

    /*public TakeQuiz insertTakeQuizResult(Questionnaire qa){
        TakeQuiz record = new TakeQuiz();
        record.setFinishTime(qa.getEndTime());
        record.setStartTime(qa.getStartTime());
        record.setScore(qa.getCorrectCount());
        record.setUserId(qa.getUserId());
        record.setQuizId(qa.getQuestionAndChoices().get(0).getQuestion().getQuizId());
        takeQuizMapper.insertSelective(record);
        return record;
    }*/

    public TakeQuizEntity insertTakeQuizResult(Questionnaire qa){
        TakeQuizEntity record = new TakeQuizEntity();
        record.setFinishTime(qa.getEndTime());
        record.setStartTime(qa.getStartTime());
        record.setScore(qa.getCorrectCount());
        UserEntity user = new UserEntity();
        user.setUserId(qa.getUserId());
        record.setUser(user);
        record.getQuiz().setQuizId((qa.getQuestionAndChoices().get(0).getQuestion().getQuizId()));
        return takeQuizDAO.saveTakeQuizRecord(record);
    }

    public List<TakeQuizEntity> findAll(){
        log.warn(Thread.currentThread().getName());
        return takeQuizDAO.findAll();
    }

    public List<TakeQuizEntity> findRecordFromTo(Integer from, Integer limit, Integer sortedBy, Integer quizId){
        log.warn(Thread.currentThread().getName());
        return takeQuizDAO.getRecordFromTo(from, limit, sortedBy, quizId);
    }

    public Long findCountOfRecordQuizIdSeletive(Integer quizId){
        log.warn(Thread.currentThread().getName());
        return takeQuizDAO.getTotalNumberOfTakeQuizRecord(quizId);
    }

    @Async("customExecutor")
    public CompletableFuture<List<TakeQuizEntity>> findAll_Async(){
        log.warn(Thread.currentThread().getName());
        return CompletableFuture.completedFuture(takeQuizDAO.findAll());
    }

    @Async("customExecutor")
    public CompletableFuture<List<TakeQuizEntity>> findRecordFromTo_Async(Integer from, Integer limit, Integer sortedBy, Integer quizId){
        log.warn(Thread.currentThread().getName());
        return CompletableFuture.completedFuture(takeQuizDAO.getRecordFromTo(from, limit, sortedBy, quizId));
    }

    @Async("customExecutor")
    public CompletableFuture<Long> findCountOfRecordQuizIdSeletive_Async(Integer quizId){
        log.warn(Thread.currentThread().getName());
        return CompletableFuture.completedFuture(takeQuizDAO.getTotalNumberOfTakeQuizRecord(quizId));
    }
}

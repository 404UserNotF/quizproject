package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.dao.QuizDAO;
import com.beaconfire.quizsystem.dao.QuizMapper;
import com.beaconfire.quizsystem.entity.hentity.QuizEntity;
import com.beaconfire.quizsystem.entity.mentity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizService {
    @Autowired
    private QuizMapper quizMapper;

    @Autowired
    private QuizDAO quizDAO;

    /*public List<Quiz> selectAll(){
        return quizMapper.selectAll();
    }*/

    public List<QuizEntity> selectAll(){
        return quizDAO.getAllQuiz();
    }

    /*public Quiz selectQuizByPrimaryKey(Integer id){
        return quizMapper.selectByPrimaryKey(id);
    }*/

    public QuizEntity selectQuizByPrimaryKey(Integer id){
        return quizDAO.selectQuizById(id);
    }
}

package com.beaconfire.quizsystem.dao;

import com.beaconfire.quizsystem.entity.hentity.QuizEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizDAO {
    @Autowired
    SessionFactory sessionFactory;

    public List<QuizEntity> getAllQuiz(){
        Session session = null;
        List<QuizEntity> quizEntities = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "FROM QuizEntity";
            Query query = session.createQuery(sql);
            quizEntities = query.list();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return quizEntities;
    }

    public QuizEntity selectQuizById(Integer id){
        Session session = null;
        QuizEntity quiz = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            quiz = session.get(QuizEntity.class, id);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return quiz;
    }
}

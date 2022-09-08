package com.beaconfire.quizsystem.dao;

import com.beaconfire.quizsystem.entity.hentity.QuizResultEntity;
import com.beaconfire.quizsystem.entity.mentity.QuizResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizResultDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public int insertQuizResult(QuizResultEntity quizResultEntity){
        Session session = null;

        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(quizResultEntity);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return 1;
    }

    public List<QuizResultEntity> getResultByTakeQuizId(Integer id){
        Session session= null;
        List<QuizResultEntity> quizResults = null;
        try {
            String hql = "From QuizResultEntity qr Where qr.takeQuiz.takeQuizId = :takeQuizId";
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("takeQuizId", id);
            quizResults = query.list();
            for(QuizResultEntity qr : quizResults){
                qr.getQuestion().getChoiceEntities().get(0);
            }
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return quizResults;
    }
}

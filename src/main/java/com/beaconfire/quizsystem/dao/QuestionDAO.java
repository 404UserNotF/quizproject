package com.beaconfire.quizsystem.dao;

import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import com.beaconfire.quizsystem.entity.hentity.QuestionEntity;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<QuestionEntity> getRandomQuestions(int quizId, int count){
        Session session = null;
        List<QuestionEntity> questions = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "FROM QuestionEntity q WHERE q.quizId = :quizId ORDER BY rand()";
            Query query = session.createQuery(sql);
            query.setParameter("quizId", quizId);
            query.setMaxResults(count);
            questions = query.list();
            for(QuestionEntity question : questions){
                for(ChoiceEntity c : question.getChoiceEntities()){
                    c.getChoiceId();
                }
            }
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return questions;
    }
}



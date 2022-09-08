package com.beaconfire.quizsystem.dao;

import com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TakeQuizDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public TakeQuizEntity saveTakeQuizRecord(TakeQuizEntity record){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(record);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return record;
    }

    public List<TakeQuizEntity> findAll() {
        Session session = null;
        List<TakeQuizEntity> takeQuizEntities = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "select t from TakeQuizEntity t order by t.startTime ASC ";
            Query query  = session.createQuery(sql);
            takeQuizEntities = query.list();
            for(TakeQuizEntity takeQuiz : takeQuizEntities){
                takeQuiz.getUser().getUserName();
            }
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return takeQuizEntities;
    }


    /**
     * Integer quizId
     *      if assigned, return the count of certain type quiz record
     *      if not, return count of all quiz type record
     * */
    public Long getTotalNumberOfTakeQuizRecord(Integer quizId) {
        Session session = null;
        String hql = "select count(t) from TakeQuizEntity t";
        Long count = 0L;
        if(quizId != -1){
            hql += " where t.quiz.quizId=:quizId";
        }
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
            if(quizId != -1){
                query.setParameter("quizId", quizId);
            }
            count = (Long) query.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return count;
    }
    /**
     * sortedBy
     *      1. by default, sort by startTime
     *      2. sort by username
     *      3. sort by category
     * quizId
     *      only display certain type of quiz
     * */
    public List<TakeQuizEntity> getRecordFromTo(Integer from, Integer limit, Integer sortedBy, Integer quizId){
        Session session = null;
        List<TakeQuizEntity> takeQuizEntities = null;
        /*String hql1 = "select t from TakeQuizEntity t order by t.startTime desc";
        String hql2 = "select t from TakeQuizEntity t order by t.user.userName desc ";
        String hql3 = "select t from TakeQuizEntity t order by t.quiz.quizId desc";*/
        String hql = "select t from TakeQuizEntity t";
        if(quizId != -1){
            hql += " where t.quiz.quizId=:quizId";
        }
        if(sortedBy == 1){
            hql += " order by t.startTime desc";
        }else if(sortedBy == 2){
            hql += " order by t.user.userName asc";
        }else{
            hql += " order by t.quiz.quizId desc";
        }
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query  = session.createQuery(hql);
            if(quizId != -1){
                query.setParameter("quizId", quizId);
            }
            query.setMaxResults(limit);
            query.setFirstResult(from);
            takeQuizEntities = query.list();
            for(TakeQuizEntity takeQuiz : takeQuizEntities){
                takeQuiz.getUser().getUserName();
            }
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return takeQuizEntities;
    }
}

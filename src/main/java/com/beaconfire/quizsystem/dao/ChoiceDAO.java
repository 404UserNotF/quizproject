package com.beaconfire.quizsystem.dao;

import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public ChoiceEntity getChoiceEntityById(int id){
        Session session = null;
        ChoiceEntity choice = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            choice = session.get(ChoiceEntity.class, id);
            choice.getQuestion();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return choice;
    }

    public void saveQuestion(List<ChoiceEntity> choiceEntities){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            for(ChoiceEntity c : choiceEntities){
                session.save(c);
            }
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
    }
}

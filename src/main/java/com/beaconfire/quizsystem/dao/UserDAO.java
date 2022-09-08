package com.beaconfire.quizsystem.dao;

import com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity;
import com.beaconfire.quizsystem.entity.hentity.UserEntity;
import com.beaconfire.quizsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    SessionFactory sessionFactory;

    public UserEntity getUserByUsername(String username){
        Session session = null;
        UserEntity user = null;
        try{
            session = HibernateUtils.getSession(sessionFactory);
            session.beginTransaction();
            String sql = "FROM UserEntity u WHERE u.userName = :username";
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            List<UserEntity> users = query.list();
            if(users.size() == 1){
                user = users.get(0);
            }
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return user;
    }

    public UserEntity insert(UserEntity newUser){
        Session session = null;
        try{
            session = HibernateUtils.getSession(sessionFactory);
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }

        return newUser;
    }

    public UserEntity getUserById(int id){
        Session session = null;
        UserEntity user = null;
        try{
            session = HibernateUtils.getSession(sessionFactory);
            session.beginTransaction();
            user = session.get(UserEntity.class, id);
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return user;
    }

    public List<UserEntity> getUserFromTo(Integer from, Integer limit){
        Session session = null;
        List<UserEntity> users = null;
        String hql = "select u from UserEntity u";
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query  = session.createQuery(hql);
            query.setMaxResults(limit);
            query.setFirstResult(from);
            users = query.list();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return users;
    }

    public long getTotalUserCount(){
        Session session = null;
        String hql = "select count(u) from UserEntity u";
        Long count = 0L;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
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

    public int changeUserStatusTo(Integer userId, String changeTo){
        Session session = null;
        String hql = "update UserEntity u set u.userStatus = :changeTo where id = :userId";
        int affectedRow = 0;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("changeTo", changeTo);
            query.setParameter("userId", userId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return affectedRow;
    }
}

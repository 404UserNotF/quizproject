package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.dao.UserDAO;
import com.beaconfire.quizsystem.dao.UserMapper;
import com.beaconfire.quizsystem.entity.hentity.UserEntity;
import com.beaconfire.quizsystem.entity.mentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDAO userDAO;

    public UserEntity findUserByUsernameHibernate(String username){
        return userDAO.getUserByUsername(username);
    }

    public void insertHibernate(UserEntity user){
        userDAO.insert(user);
    }

    /*public User findUserById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }*/

    public UserEntity findUserById(Integer id){
        return userDAO.getUserById(id);
    }

    public List<UserEntity> getUserFromTo(Integer from, Integer limit){
        return userDAO.getUserFromTo(from, limit);
    }

    public long getAllUserCount(){
        return userDAO.getTotalUserCount();
    }

    public int changeUserStatusTo(Integer userId, String changeTo){
        return userDAO.changeUserStatusTo(userId, changeTo);
    }
}

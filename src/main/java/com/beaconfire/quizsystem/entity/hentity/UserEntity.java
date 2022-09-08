package com.beaconfire.quizsystem.entity.hentity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "bf_project1")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;
    @Basic
    @Column(name = "user_password", nullable = false, length = 20)
    private String userPassword;
    @Basic
    @Column(name = "user_email", nullable = false, length = 30)
    private String userEmail;
    @Basic
    @Column(name = "user_role", nullable = false, length = 10)
    private String userRole;
    @Basic
    @Column(name = "user_status", nullable = false, length = 10)
    private String userStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TakeQuizEntity> takeQuizs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<FeedbackEntity> feedbacks = new HashSet<>();

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userStatus='" + userStatus + '\'';
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Set<TakeQuizEntity> getTakeQuizs() {
        return takeQuizs;
    }

    public void setTakeQuizs(Set<TakeQuizEntity> takeQuizs) {
        this.takeQuizs = takeQuizs;
    }

    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}

package com.beaconfire.quizsystem.entity.hentity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "take_quiz", schema = "bf_project1", catalog = "")

public class TakeQuizEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "take_quiz_id", nullable = false)
    private Integer takeQuizId;
    @Basic
    @Column(name = "start_time", nullable = false)
    private Long startTime;
    @Basic
    @Column(name = "finish_time", nullable = false)
    private Long finishTime;
    @Basic
    @Column(name = "score", nullable = false)
    private Integer score;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="takeQuiz", cascade=CascadeType.ALL)
    private Set<QuizResultEntity> quizResults = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="quiz_id", nullable = false)
    private QuizEntity quiz = new QuizEntity();

    @Override
    public String toString() {
        return "TakeQuizEntity{" +
                "takeQuizId=" + takeQuizId +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", score=" + score +
                ", user=" + user;
    }

    public Set<QuizResultEntity> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(Set<QuizResultEntity> quizResults) {
        this.quizResults = quizResults;
    }

    public Integer getTakeQuizId() {
        return takeQuizId;
    }

    public void setTakeQuizId(Integer takeQuizId) {
        this.takeQuizId = takeQuizId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }
}

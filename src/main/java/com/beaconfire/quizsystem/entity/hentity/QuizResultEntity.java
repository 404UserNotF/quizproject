package com.beaconfire.quizsystem.entity.hentity;

import javax.persistence.*;

@Entity
@Table(name = "quiz_result", schema = "bf_project1", catalog = "")
public class QuizResultEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "quiz_result_id", nullable = false)
    private Integer quizResultId;
    /*@Basic
    @Column(name = "question_id", nullable = false)
    private Integer questionId;*/
    @Basic
    @Column(name = "user_choice", nullable = false, length = 20)
    private String userChoice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "take_quiz_id")
    private TakeQuizEntity takeQuiz;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public Integer getQuizResultId() {
        return quizResultId;
    }

    public void setQuizResultId(Integer quizResultId) {
        this.quizResultId = quizResultId;
    }

    /*public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }*/

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public TakeQuizEntity getTakeQuiz() {
        return takeQuiz;
    }

    public void setTakeQuiz(TakeQuizEntity takeQuiz) {
        this.takeQuiz = takeQuiz;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizResultEntity that = (QuizResultEntity) o;

        if (quizResultId != null ? !quizResultId.equals(that.quizResultId) : that.quizResultId != null) return false;
        if (userChoice != null ? !userChoice.equals(that.userChoice) : that.userChoice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quizResultId != null ? quizResultId.hashCode() : 0;
        result = 31 * result + (userChoice != null ? userChoice.hashCode() : 0);
        return result;
    }
}

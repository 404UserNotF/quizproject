package com.beaconfire.quizsystem.entity.hentity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "question", schema = "bf_project1", catalog = "")
public class QuestionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "question_id", nullable = false)
    private Integer questionId;
    @Basic
    @Column(name = "quiz_id", nullable = false)
    private Integer quizId;
    @Basic
    @Column(name = "question_content", nullable = false, length = 150)
    private String questionContent;
    @Basic
    @Column(name = "question_status", nullable = false, length = 10)
    private String questionStatus;
    @OneToMany(mappedBy="question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChoiceEntity> choiceEntities = new ArrayList<>();

    public List<ChoiceEntity> getChoiceEntities() {
        return choiceEntities;
    }

    public void setChoiceEntities(List<ChoiceEntity> choiceEntities) {
        this.choiceEntities = choiceEntities;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (questionId != null ? !questionId.equals(that.questionId) : that.questionId != null) return false;
        if (quizId != null ? !quizId.equals(that.quizId) : that.quizId != null) return false;
        if (questionContent != null ? !questionContent.equals(that.questionContent) : that.questionContent != null)
            return false;
        if (questionStatus != null ? !questionStatus.equals(that.questionStatus) : that.questionStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId != null ? questionId.hashCode() : 0;
        result = 31 * result + (quizId != null ? quizId.hashCode() : 0);
        result = 31 * result + (questionContent != null ? questionContent.hashCode() : 0);
        result = 31 * result + (questionStatus != null ? questionStatus.hashCode() : 0);
        return result;
    }
}

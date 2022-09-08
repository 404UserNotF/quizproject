package com.beaconfire.quizsystem.entity.hentity;

import javax.persistence.*;

@Entity
@Table(name = "quiz", schema = "bf_project1", catalog = "")
public class QuizEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "quiz_id", nullable = false)
    private Integer quizId;
    @Basic
    @Column(name = "quiz_name", nullable = false, length = 20)
    private String quizName;
    @Basic
    @Column(name = "quiz_description", nullable = false, length = 100)
    private String quizDescription;

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizEntity that = (QuizEntity) o;

        if (quizId != null ? !quizId.equals(that.quizId) : that.quizId != null) return false;
        if (quizName != null ? !quizName.equals(that.quizName) : that.quizName != null) return false;
        if (quizDescription != null ? !quizDescription.equals(that.quizDescription) : that.quizDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quizId != null ? quizId.hashCode() : 0;
        result = 31 * result + (quizName != null ? quizName.hashCode() : 0);
        result = 31 * result + (quizDescription != null ? quizDescription.hashCode() : 0);
        return result;
    }
}

package com.beaconfire.quizsystem.entity.hentity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "feedback", schema = "bf_project1", catalog = "")
public class FeedbackEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "feedback_id", nullable = false)
    private Integer feedbackId;
    @Basic
    @Column(name = "quiz_id", nullable = false)
    private Integer quizId;
    @Basic
    @Column(name = "rating", nullable = false)
    private Integer rating;
    @Basic
    @Column(name = "review", nullable = false, length = 200)
    private String review;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @Override
    public String toString() {
        return "FeedbackEntity{" +
                "feedbackId=" + feedbackId +
                ", quizId=" + quizId +
                ", rating=" + rating +
                ", review='" + review +
                ", user=" + user;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

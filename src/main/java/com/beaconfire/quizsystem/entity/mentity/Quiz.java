package com.beaconfire.quizsystem.entity.mentity;

public class Quiz {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quiz.quiz_id
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private Integer quizId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quiz.quiz_name
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private String quizName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quiz.quiz_description
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private String quizDescription;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quiz.quiz_id
     *
     * @return the value of quiz.quiz_id
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public Integer getQuizId() {
        return quizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quiz.quiz_id
     *
     * @param quizId the value for quiz.quiz_id
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quiz.quiz_name
     *
     * @return the value of quiz.quiz_name
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quiz.quiz_name
     *
     * @param quizName the value for quiz.quiz_name
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setQuizName(String quizName) {
        this.quizName = quizName == null ? null : quizName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quiz.quiz_description
     *
     * @return the value of quiz.quiz_description
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public String getQuizDescription() {
        return quizDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quiz.quiz_description
     *
     * @param quizDescription the value for quiz.quiz_description
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription == null ? null : quizDescription.trim();
    }
}
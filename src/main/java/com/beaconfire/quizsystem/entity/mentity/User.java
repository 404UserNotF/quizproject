package com.beaconfire.quizsystem.entity.mentity;




public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_id
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_name
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_password
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private String userPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_email
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private String userEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_role
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private String userRole;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_status
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    private String userStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_id
     *
     * @return the value of user.user_id
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_id
     *
     * @param userId the value for user.user_id
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_name
     *
     * @return the value of user.user_name
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_name
     *
     * @param userName the value for user.user_name
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_password
     *
     * @return the value of user.user_password
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_password
     *
     * @param userPassword the value for user.user_password
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_email
     *
     * @return the value of user.user_email
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_email
     *
     * @param userEmail the value for user.user_email
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_role
     *
     * @return the value of user.user_role
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_role
     *
     * @param userRole the value for user.user_role
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_status
     *
     * @return the value of user.user_status
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_status
     *
     * @param userStatus the value for user.user_status
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }
}
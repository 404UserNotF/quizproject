package com.beaconfire.quizsystem.dao;


import com.beaconfire.quizsystem.entity.mentity.QuizResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuizResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quiz_result
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    int deleteByPrimaryKey(Integer quizResultId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quiz_result
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    int insert(QuizResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quiz_result
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    int insertSelective(QuizResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quiz_result
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    QuizResult selectByPrimaryKey(Integer quizResultId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quiz_result
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    int updateByPrimaryKeySelective(QuizResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quiz_result
     *
     * @mbggenerated Fri Aug 12 18:04:00 EDT 2022
     */
    int updateByPrimaryKey(QuizResult record);
}
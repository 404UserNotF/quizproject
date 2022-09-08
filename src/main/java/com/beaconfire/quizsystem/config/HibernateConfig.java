package com.beaconfire.quizsystem.config;

import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    @Bean
    public SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                /*.addClass(com.beaconfire.quizsystem.entity.hentity.UserEntity.class)
                .addClass(com.beaconfire.quizsystem.entity.hentity.ChoiceEntity.class)
                .addClass(com.beaconfire.quizsystem.entity.hentity.FeedbackEntity.class)
                .addClass(com.beaconfire.quizsystem.entity.hentity.QuestionEntity.class)
                .addClass(com.beaconfire.quizsystem.entity.hentity.QuizEntity.class)
                .addClass(com.beaconfire.quizsystem.entity.hentity.QuizResultEntity.class)
                .addClass(com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity.class)*/
                .buildSessionFactory();
        return sessionFactory;
    }
}

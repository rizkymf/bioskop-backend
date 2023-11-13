package org.binaracademy.bioskopbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.Spring

@Configuration
public class QuartzScheduler {

    private final ApplicationContext applicationContext;

    @Autowired
    public QuartzScheduler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

//    @Bean
//    public SpringBeanJobFactory springBeanJobFactory() {
//
//    }
}

package com.gudong.dbmbackground.config;

import com.gudong.dbm.constant.RabbitMQConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description mq配置类
 *
 * @author maggie
 * @date 2021-07-17 17:49
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMQConstant.BACKGROUND_EXCHANGE);
    }
}

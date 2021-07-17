package com.gudong.dbmitemservice.config;

import com.gudong.dbm.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description mq配置
 *
 * @author maggie
 * @date 2021-07-17 17:55
 */
@Configuration
public class RabbitMQConfig {
    //1.声明队列
    @Bean
    public Queue queue(){
        return new Queue(RabbitMQConstant.BACKGROUND_PRODUCT_SAVE_UPDATE_ITEM_QUEUE);
    }
    //2.声明交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMQConstant.BACKGROUND_EXCHANGE);
    }
    //3.建立绑定关系
    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("product.*");
    }
}

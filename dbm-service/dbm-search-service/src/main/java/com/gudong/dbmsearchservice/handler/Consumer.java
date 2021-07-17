package com.gudong.dbmsearchservice.handler;

import com.gudong.dbm.constant.RabbitMQConstant;
import com.gudong.dbm.service.ISearchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * description 消费者
 *
 * @author maggie
 * @date 2021-07-17 18:01
 */
@Component
public class Consumer {

    @Autowired
    private ISearchService searchService;

    @RabbitListener(queues = RabbitMQConstant.BACKGROUND_PRODUCT_SAVE_UPDATE_QUEUE)
    public void get(Long productId){
        System.out.println("search service get id:"+productId);
        searchService.updateById(productId);
    }
}

package com.gudong.dbmitemservice.handler;

import com.gudong.dbm.constant.RabbitMQConstant;
import com.gudong.dbm.service.IItemService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IItemService itemService;

    @RabbitListener(queues = RabbitMQConstant.BACKGROUND_PRODUCT_SAVE_UPDATE_ITEM_QUEUE)
    public void get(Long productId){
        System.out.println("item service get id:"+productId);
        itemService.createHtmlById(productId);
    }
}

package com.gudong.dbm.constant;

/**
 * description mq队列、交换机等名称常量配置类
 *
 * @author maggie
 * @date 2021-07-17 17:51
 */
public interface RabbitMQConstant {


    String BACKGROUND_EXCHANGE = "background-exchange";

    String BACKGROUND_PRODUCT_SAVE_UPDATE_QUEUE = "background-product-search-add-queue";

    String BACKGROUND_PRODUCT_SAVE_UPDATE_ITEM_QUEUE = "background-product-item-add-queue";
}

CREATE TABLE `t_product_type` (
`id` bigint NOT NULL AUTO_INCREMENT,
`pid` bigint NOT NULL,
`name` varchar(12) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `t_product` (
`id` bigint NOT NULL AUTO_INCREMENT,
`name` varchar(12) NOT NULL,
`price` bigint NOT NULL,
`sale_price` bigint NOT NULL,
`sale_point` varchar(64) NOT NULL,
`image` varchar(256) NOT NULL,
`stock` bigint NOT NULL,
`flag` tinyint(1) NOT NULL,
`create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`create_user` bigint NOT NULL,
`update_user` bigint NOT NULL,
`type_id` bigint NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `t_product_desc` (
`id` bigint NOT NULL AUTO_INCREMENT,
`p_desc` text NOT NULL,
`product_id` bigint NOT NULL,
PRIMARY KEY (`id`)
);

-- 添加 type 测试数据
INSERT INTO `t_product_type` VALUES (1, -1, '电器');
INSERT INTO `t_product_type` VALUES (2, 1, '彩电');
INSERT INTO `t_product_type` VALUES (3, 1, '冰箱');
INSERT INTO `t_product_type` VALUES (4, 1, '洗衣机');
INSERT INTO `t_product_type` VALUES (5, -1, '手机');
INSERT INTO `t_product_type` VALUES (6, 5, '小米');
INSERT INTO `t_product_type` VALUES (7, 5, '华为');
INSERT INTO `t_product_type` VALUES (8, 5, '苹果');
-- 添加 prod 测试数据
insert into t_product values(1,'戴尔电脑',3000,5000,'好','img/xx.png',200,1,SYSDATE(),SYSDATE(),1,1,1);
insert into t_product values(2,'海信电视机',3000,5000,'好','img/xx.png',200,1,SYSDATE(),SYSDATE(),1,1,2);
insert into t_product values(3,'海尔冰箱',3000,5000,'好','img/xx.png',200,1,SYSDATE(),SYSDATE(),1,1,3);
insert into t_product values(4,'小米手机',3000,5000,'好','img/xx.png',200,1,SYSDATE(),SYSDATE(),1,1,6);
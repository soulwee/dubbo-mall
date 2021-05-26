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


/**
 * 创建数据库
 */
CREATE DATABASE mplat DEFAULT CHARSET=UTF8;

GRANT ALL PRIVILEGES ON mplat.* TO 'mplat'@'%' IDENTIFIED BY 'mplat';
GRANT ALL PRIVILEGES ON mplat.* TO 'mplat'@'localhost' IDENTIFIED BY 'mplat';

drop table if exists mplat_user_info;
CREATE TABLE mplat_user_info (
    id bigint primary key auto_increment,
    usr_name varchar(128) unique COMMENT '用户名',
    usr_passwd varchar(256) COMMENT '用户密码'
)Engine=InnoDB DEFAULT CHARSET=UTF8 COMMENT '用户信息';

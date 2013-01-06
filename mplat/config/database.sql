/**
 * 创建数据库
 */
CREATE DATABASE mplat DEFAULT CHARSET=UTF8;

GRANT ALL PRIVILEGES ON mplat.* TO 'mplat'@'%' IDENTIFIED BY 'mplat';
GRANT ALL PRIVILEGES ON mplat.* TO 'mplat'@'localhost' IDENTIFIED BY 'mplat';

drop table if exists mplat_mutex_ticket;
CREATE TABLE mplat_mutex_ticket (
    name varchar(64) primary key,
    value bigint,
    stamp bigint
)Engine=InnoDB DEFAULT CHARSET=UTF8;
INSERT INTO mplat_mutex_ticket VALUES('USER-INFO-ID', 0, 0);

drop table if exists mplat_user_info;
CREATE TABLE mplat_user_info (
    id bigint primary key,
    usr_name varchar(128) unique COMMENT '用户名',
    usr_passwd varchar(256) COMMENT '用户密码'
)Engine=InnoDB DEFAULT CHARSET=UTF8 COMMENT '用户信息';
/**
 * �������ݿ�
 */
CREATE DATABASE mplat DEFAULT CHARSET=UTF8;

GRANT ALL PRIVILEGES ON mplat.* TO 'mplat'@'%' IDENTIFIED BY 'mplat';
GRANT ALL PRIVILEGES ON mplat.* TO 'mplat'@'localhost' IDENTIFIED BY 'mplat';

drop table if exists mplat_user_info;
CREATE TABLE mplat_user_info (
    id bigint primary key auto_increment,
    usr_name varchar(128) unique COMMENT '�û���',
    usr_passwd varchar(256) COMMENT '�û�����'
)Engine=InnoDB DEFAULT CHARSET=UTF8 COMMENT '�û���Ϣ';

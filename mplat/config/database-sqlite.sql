
drop table if exists mplat_mutex_ticket;
CREATE TABLE mplat_mutex_ticket (
    name varchar(64) primary key,
    value bigint,
    stamp bigint
);
INSERT INTO mplat_mutex_ticket VALUES('USER-INFO-ID', 0, 0);

drop table if exists mplat_user_info;
CREATE TABLE mplat_user_info (
    id bigint primary key,
    usr_name varchar(128) unique,
    usr_passwd varchar(256)
);





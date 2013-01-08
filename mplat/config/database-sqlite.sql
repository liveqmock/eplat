
DROP TABLE IF EXISTS mplat_mutex_ticket;
CREATE TABLE mplat_mutex_ticket (
    name VARCHAR(64) PRIMARY KEY,
    value BIGINT,
    stamp BIGINT
);
INSERT INTO mplat_mutex_ticket VALUES('TB-UserInfo-ID', 0, 0);
INSERT INTO mplat_mutex_ticket VALUES('TB-ExamInfo-ID', 0, 0);
INSERT INTO mplat_mutex_ticket VALUES('TB-ExamItem-ID', 0, 0);

DROP TABLE IF EXISTS mplat_user_info;
CREATE TABLE mplat_user_info (
    id BIGINT PRIMARY KEY,
    usr_name VARCHAR(128) UNIQUE,
    usr_passwd VARCHAR(256)
);

DROP TABLE IF EXISTS mplat_exam_info;
CREATE TABLE mplat_exam_info (
    id BIGINT PRIMARY KEY,
    title VARCHAR(1024),
    rgt_no VARCHAR(64)
);

DROP TABLE IF EXISTS mplat_exam_item;
CREATE TABLE mplat_exam_item(
    id BIGINT PRIMARY KEY,
    no VARCHAR(64),
    exm_id BIGINT,
    text VARCHAR(1024),
    UNIQUE(no, exm_id)
);

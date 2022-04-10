drop table if exists bitcoin;

create table bitcoin
(
    `name`              CHAR(4)         NOT NULL,
    `name_zh`           VARCHAR(16)     NOT NULL,
    `rate`              DECIMAL(10,4)   NOT NULL,
    `create_time`       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    primary key (`name`)
);
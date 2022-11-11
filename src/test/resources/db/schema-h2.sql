DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id           BIGINT(20)  NOT NULL COMMENT '主键ID',
    name         VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age          INT(11)     NULL DEFAULT NULL COMMENT '年龄',
    email        VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    tenant_id    bigint      NULL default null comment '租户id',
    last_updated date        NULL comment '更新时间',
    date_created date        NULL comment '创建时间',
    PRIMARY KEY (id)
);

CREATE TABLE message
(
    id           BIGINT(20) NOT NULL COMMENT '主键ID',
    user_id      bigint     not null comment '用户id',
    msg          text       not null comment '消息内容',
    tenant_id    bigint     NULL default null comment '租户id',
    last_updated datetime   NULL comment '更新时间',
    date_created datetime   NULL comment '创建时间',
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    id           BIGINT(20)  NOT NULL COMMENT '主键ID',
    name         VARCHAR(50) NULL DEFAULT NULL COMMENT '角色名',
    last_updated datetime    NULL comment '更新时间',
    date_created datetime    NULL comment '创建时间',
    PRIMARY KEY (id)
);
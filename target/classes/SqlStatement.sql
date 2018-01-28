#创建数据表
create database web_qq_db;
use web_qq_db;
create table user_set(id varchar(20) primary key, flag tinyint(1))ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table user(id varchar(30) primary key, imageUri varchar(200), sex varchar(10), age int, signature varchar(40), alive boolean default false);
create table image(imageId varchar(40) primary key, imageUri varchar(300));

#插入图片icon


#添加用户空间
insert into user_set values("abc", 0);
insert into user_set values("123", 0);
insert into user_set values("wang", 0);
insert into user_set values("kun", 0);
insert into user_set values("luo", 0);
insert into user_set values("090201", 0);
insert into user_set values("090202", 0);
insert into user_set values("090203", 0);
insert into user_set values("090204", 0);
insert into user_set values("090205", 0);
insert into user_set values("090206", 0);
insert into user_set values("090207", 0);
insert into user_set values("090208", 0);
insert into user_set values("090209", 0);
insert into user_set values("090211", 0);
insert into user_set values("090212", 0);
insert into user_set values("090213", 0);
insert into user_set values("090214", 0);
insert into user_set values("090215", 0);
insert into user_set values("090216", 0);
insert into user_set values("090217", 0);
insert into user_set values("090218", 0);
insert into user_set values("090219", 0);
insert into user_set values("090221", 0);
insert into user_set values("090222", 0);
insert into user_set values("090223", 0);
insert into user_set values("090224", 0);
insert into user_set values("090225", 0);
insert into user_set values("090226", 0);
insert into user_set values("090227", 0);
insert into user_set values("090228", 0);
insert into user_set values("090229", 0);
insert into user_set values("090231", 0);
insert into user_set values("090232", 0);
insert into user_set values("090233", 0);
insert into user_set values("090234", 0);
insert into user_set values("090235", 0);
insert into user_set values("090236", 0);
insert into user_set values("090237", 0);
insert into user_set values("090238", 0);
insert into user_set values("090239", 0);


/*user table*/
create table User(id varchar(20) primary key, imageUri varchar(200), sex varchar(10), age int, signature varchar(40));
/*image table*/
create table Image(imageId varchar(40) primary key, imageUri varchar(300));
/*chat log*/
create table record_luo_wang(srcId varchar(20), dstId varchar(20), content varchar(512), timestamp Date,constraint foreign key (srcId) references User(id), foreign key (dstId) references User(id));
/*friend table*/    
create table friend_123 (friendId varchar(20) DEFAULT NULL, friendImgUri varchar(200) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8    
/*UserSet*/
create table user_set(id varchar(20) primary key, flag boolean)  character set = utf8;
/*history_123*/
create table history_123(friendId varchar(20), content varchar(512), timestamp datetime) character set = utf8;
//插入未读消息
insert into history_sss values('abc', 'content2&#xA9;&#x2693;&#x1F004', '2017-11-14 04:14:24');

//插入图片icon
insert into image valuse('defaultIcon', 'http://localhost:8080/SpringMVC/images/defaultIcon.jpg');
insert into image valuse('defaultIcon1', 'http://localhost:8080/SpringMVC/images/defaultIcon1.jpg');
insert into image valuse('defaultIcon2', 'http://localhost:8080/SpringMVC/images/defaultIcon2.jpg');
insert into image valuse('defaultIcon3', 'http://localhost:8080/SpringMVC/images/defaultIcon3.jpg');

//添加用户空间
insert into user_set values('abc', 1);

//添加用户
insert into user values('abc', 'http://localhost:8080/SpringMVC/images/defaultIcon.jpg', 'woman', 11, 'signature');


//添加好友
insert into friend_123 values('abc', 'http://localhost:8080/SpringMVC/images/defaultIcon\.jpg');   
insert into friend_luo values('123', 'http://localhost:8080/SpringMVC/images/defaultIcon\.jpg');   
create table record_123_abc(srcId varchar(20), dstId varchar(20), content varchar(512), constraint foreign key (srcId) references User(id), foreign key (dstId) references User(id));

//添加未读历史消息

alter table User drop foreign key user_ibfk_1;


//插入聊天数据
insert into record_abc_123 values("abc", "123", 'abc says', '2017-11-16 下午04:14:24');

//聊天数据按时间逆序输出 优先输出最近的消息
SELECT * FROM `record_123_abc` order by timestamp desc limit 0,2


//修改用户头像

UPDATE user SET imageUri='ssssssdefaultdddIcon.jpg' WHERE id = 'luo';
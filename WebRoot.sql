create database BBS;
use BBS;
create table main_forum(
	id int not null primary key auto_increment,
	title varchar(20) not null,
	info varchar(60) 
)charset=utf8;

INSERT INTO `main_forum` 
VALUES ('1','移动开发','移动开发'),
 ('2','网站开发','网站开发');
 

create table sub_forum(
	id int not null primary key auto_increment,
	main_forum int not null, 
	title varchar(20) not null,
	info varchar(60),
	FOREIGN key(main_forum) references main_forum(id) on delete cascade on update cascade
)charset=utf8;

INSERT INTO `sub_forum`
 VALUES ('1','1','android开发','android入门到精通'),
 ('2','1','ios开发','ios从入门到精通'), 
 ('3','2','php基础','php入门'),
 ('4','2','php框架','yii，thinkphp框架等'),
 ('5','2','javaee','javaee学习');

create table user(
	id int not null primary key auto_increment,
	username varchar(20) not null,
	password varchar(20) not null,
    sex varchar(5),
    photo_url tinytext,
	email varchar(30) not null,
	type int not null,       #类型
	register_date datetime not null,  #注册时间
    signature varchar(50),     #签名
	level int not null default 0 , #等级
    active_code varchar(32),	#激活码
    hasActive int	#用户状态状态
)charset=utf8;

INSERT INTO `user` 
VALUES ('1','qq123','123','男',NULL,'123@qq.com','0','2016-03-17 21:54:37','hello','0',NULL,1)
, ('2','张建浩','123','女',NULL,'456@qq.com','0','2016-03-16 21:55:33','空你起哇','0',NULL,1);


create table admin(
	id int not null primary key auto_increment,
	user_name varchar(20) not null,
	password varchar(20) not null,
    photo_url tinytext,
	email varchar(30) not null,
	type int not null,
    sex varchar(10)
)charset=utf8;

INSERT INTO 
`admin` VALUES ('1','admin1','123','/upload/default/head_icon.jpg','admin1@qq.com',0,'男'), 
('2','admin2','123','/upload/default/head_icon.jpg','admin2@qq.com',1,'男');

#帖子表
create table post(
	id int not null primary key auto_increment,
	forum int not null,   #属于的子版块
	user_id int not null,  #发帖用户
	title varchar(40) not null,  #标题
	card_content varchar(500)not null,  #发帖内容
	send_date datetime not null,    #发帖时间
    post_type int, #帖子类型（普通，精华帖,由管理员设置)
    reply_num int,#帖子回复数目
    view_num int,
	FOREIGN key(forum) references sub_forum(id) on delete cascade on update cascade,
	FOREIGN key(user_id) references user(id) on delete cascade on update cascade
)charset=utf8;


#跟帖表
create table followcard(
	id int not null primary key auto_increment,
	send_id int not null,   #属于的帖子
	user_id int not null,
	follow_content varchar(200),  #跟帖内容
	follow_date datetime not null,
	FOREIGN key(send_id) references post(id) on delete cascade on update cascade,
	FOREIGN key(user_id) references user(id) on delete cascade on update cascade
)charset=utf8;


 
 
#公告表
create table notice(
	id int not null primary key auto_increment,
	admin_id int not null,  #发布的管理员
	content varchar(200),  #内容
	notice_date datetime not null,  #公告时间
    title varchar(45),
	FOREIGN key(admin_id) references admin(id) on delete cascade on update cascade
)charset=utf8;
insert into notice values (1,1,'社区问答-数据结构背后的原理','2016-03-17 22:09:38','社区问答-数据结构背后的原理'),
(2,1,'不容错过，知识库精华资源推荐','2016-03-20 23:45:00','不容错过，知识库精华资源推荐'),
(3,1,'2016年上半年热门下载资源','2016-03-18 22:09:38','2016年上半年热门下载资源'),
(4,1,'问答2016年3月活动开始啦！','2016-03-19 22:09:38','问答2016年3月活动开始啦！');

#精华帖申请列表
CREATE TABLE best_post(
	 id int not null primary key auto_increment,
     post_id int not null,
     user_id int not null,
     apply_date timestamp,
     state int,
     FOREIGN key(post_id) references post(id) on delete cascade on update cascade,
     FOREIGN key(user_id) references user(id) on delete cascade on update cascade
) charset=utf8;

#用户黑名单，用于管理员管理用户权限
CREATE TABLE black_list(
	id int not null primary key auto_increment,
    user_id int not null,
    level int,#用户拦截等级，1，代表最高限制，禁止用户登陆账号，2禁止用户发帖，同时禁止回复 3禁止用户发帖 4禁止用户回复
    FOREIGN key(user_id) references user(id) on delete cascade on update cascade
)charset=utf8;










 
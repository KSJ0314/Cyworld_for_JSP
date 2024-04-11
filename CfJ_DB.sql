create database cyworld;
-- create 후 utf-8 설정 필수 

use cyworld;

-- 회원
create table member(
	id varchar(20) primary key,
    pw varchar(20) not null,
    email varchar(50),
    tel varchar(13),
    isAdmin boolean default false,
    imgName varchar(100),
    last_view int
);

-- 방명록
create table guestbook(
	no int auto_increment primary key,
    id varchar(20),
    post_id varchar(20),
    created datetime default current_timestamp,
    content varchar(300),
    foreign key (id)
		references member (id)
        on delete cascade
        on update cascade,
    foreign key (post_id)
		references member (id)
        on delete set null
        on update cascade
);

-- 방명록의 댓글
create table guestbookreply (
	r_no int auto_increment primary key,
    g_no int,
    content varchar(100),
    created datetime default current_timestamp,
    id varchar(20),
    foreign key (g_no)
		references guestbook (no)
        on delete cascade,
	foreign key (id)
		references member (id)
        on delete set null
        on update cascade
);

-- 사진첩
create table images(
	no int auto_increment primary key,
    id varchar(20),
    title varchar(50),
    content varchar(1000),
    created datetime default current_timestamp,
    imgName varchar(100),
    foreign key (id)
		references member (id)
        on delete cascade
        on update cascade
);

create table music(
	no int auto_increment primary key,
    id varchar(20),
    path varchar(50),
    title varchar(50),
    artist varchar(50),
    isPlay boolean default false,
    foreign key (id)
		references member (id)
        on delete cascade
        on update cascade
);
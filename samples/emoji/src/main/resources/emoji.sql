 drop schema if exists emoji;
 create schema `emoji` default character set utf8mb4 ;

 drop user 'emoji'@'localhost';
 create user 'emoji'@'localhost' identified by 'password';
 grant all on emoji.* to 'emoji'@'localhost';
 
 drop user 'emoji'@'%';
 create user 'emoji'@'%' identified by 'password';
 grant all on emoji.* to 'emoji'@'%';
 flush privileges;


use emoji;

drop table if exists users;
create table users
(
  id                   int not null auto_increment,  
  first_name                varchar(20) null,
  last_name                 varchar(20) null,
  email_id                  varchar(50) not null,
  login_name                varchar(50) null,
  avatar					varchar(50) null comment 'supports emoji characters',
  created_timestamp timestamp not null default now() comment 'record creation timestamp' ,
  last_updated_timestamp timestamp not null default now() comment 'record modification timestamp',
  primary key (id),
  unique index user_unique (email_id asc)
)
default character set = utf8mb4
COLLATE utf8mb4_bin
comment = 'this table stores user information including a emoji characters';




-- Emoji Unicode characters
-- http://apps.timwhitlock.info/emoji/tables/unicode 

-- Emoji MySql
-- https://mathiasbynens.be/notes/mysql-utf8mb4
-- http://info.michael-simons.eu/2013/01/21/java-mysql-and-multi-byte-utf-8-support/
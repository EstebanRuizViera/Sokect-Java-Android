drop database if exists chat;
create database chat;

use chat;


create table comments(
  nick VARCHAR(200) NOT NULL,
  commentary VARCHAR(200) NOT NULL
);


select * from comments;


# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint auto_increment not null,
  user_name                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  photo                     longblob,
  photo2                    longblob,
  created                   datetime(6),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  account_number            varchar(255) not null,
  bank                      varchar(255),
  name                      varchar(255),
  constraint pk_account primary key (account_number))
;

create table attached_file (
  id                        varchar(255) not null,
  path                      varchar(255),
  file_name                 varchar(255),
  content_type              varchar(255),
  constraint pk_attached_file primary key (id))
;

create table business (
  id                        varchar(255) not null,
  cost_of_maintenace        integer,
  selling_price             integer,
  constraint pk_business primary key (id))
;

create table line (
  id                        varchar(255) not null,
  person_id                 varchar(255),
  number_1                  varchar(255),
  number_2                  varchar(255),
  number_3                  varchar(255),
  usim                      varchar(255),
  join_date                 timestamp,
  keep_pay_system_date      timestamp,
  duty_period_date          timestamp,
  cancel_date               timestamp,
  pay_back_style            integer,
  keep_installment_date     timestamp,
  pay_back_date             timestamp,
  pay_installment_yn        boolean,
  check_payback_yn          boolean,
  business_info_id          varchar(255),
  account_account_number    varchar(255),
  capture_file_id           varchar(255),
  constraint ck_line_pay_back_style check (pay_back_style in (0,1)),
  constraint uq_line_business_info_id unique (business_info_id),
  constraint uq_line_capture_file_id unique (capture_file_id),
  constraint pk_line primary key (id))
;

create table person (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_person primary key (id))
;

create table sequence (
  sequence_name             varchar(255) not null,
  next_sequence             bigint,
  constraint uq_sequence_sequence_name unique (sequence_name))
;

create table admin_user (
  id                        varchar(255) not null,
  email                     varchar(255) not null,
  name                      varchar(255),
  auth_token                varchar(255),
  sha_password              varchar(255),
  constraint uq_admin_user_email unique (email),
  constraint pk_admin_user primary key (id))
;

alter table line add constraint fk_line_person_1 foreign key (person_id) references person (id);
create index ix_line_person_1 on line (person_id);
alter table line add constraint fk_line_businessInfo_2 foreign key (business_info_id) references business (id);
create index ix_line_businessInfo_2 on line (business_info_id);
alter table line add constraint fk_line_account_3 foreign key (account_account_number) references account (account_number);
create index ix_line_account_3 on line (account_account_number);
alter table line add constraint fk_line_captureFile_4 foreign key (capture_file_id) references attached_file (id);
create index ix_line_captureFile_4 on line (capture_file_id);



# --- !Downs

drop table if exists account cascade;

drop table if exists attached_file cascade;

drop table if exists business cascade;

drop table if exists line cascade;

drop table if exists person cascade;

drop table if exists sequence cascade;

drop table if exists admin_user cascade;


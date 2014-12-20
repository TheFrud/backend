# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aggregate (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  description               varchar(255),
  score                     bigint,
  user_id                   bigint,
  constraint pk_aggregate primary key (id))
;

create table person (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  age                       bigint,
  constraint pk_person primary key (id))
;

create table resource (
  id                        bigint auto_increment not null,
  aggregate_id              bigint not null,
  title                     varchar(255),
  link                      varchar(255),
  description               varchar(255),
  constraint pk_resource primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

alter table aggregate add constraint fk_aggregate_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_aggregate_user_1 on aggregate (user_id);
alter table resource add constraint fk_resource_aggregate_2 foreign key (aggregate_id) references aggregate (id) on delete restrict on update restrict;
create index ix_resource_aggregate_2 on resource (aggregate_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table aggregate;

drop table person;

drop table resource;

drop table user;

SET FOREIGN_KEY_CHECKS=1;


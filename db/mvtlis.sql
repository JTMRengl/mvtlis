/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/25 15:33:06                           */
/*==============================================================*/


DROP DATABASE IF EXISTS `mvtlis`;
CREATE DATABASE `mvtlis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mvtlis`;

/*==============================================================*/
/* Table: CLASSROOM                                             */
/*==============================================================*/
create table CLASSROOM
(
   CLASSROOM_ID         int not null auto_increment,
   CODE                 char(20),
   NAME                 char(30),
   DELETE_FLAG          int,
   primary key (CLASSROOM_ID)
);

/*==============================================================*/
/* Table: COMMENT                                               */
/*==============================================================*/
create table COMMENT
(
   COMMENT_ID           int not null auto_increment,
   COURSE_ID            int,
   CODE                 char(20),
   CONTENT              varchar(100),
   TIME                 datetime,
   primary key (COMMENT_ID)
);

/*==============================================================*/
/* Table: COURSE                                                */
/*==============================================================*/
create table COURSE
(
   COURSE_ID            int not null auto_increment,
   TESTROOM_ID          int,
   CLASSROOM_ID         int,
   NAME                 char(30),
   START_TIME           datetime,
   END_TIME             datetime,
   SPEAKER              char(10),
   REMARK               varchar(100),
   STATUS               int,
   DELETE_FLAG          int,
   primary key (COURSE_ID)
);

/*==============================================================*/
/* Table: TESTROOM                                              */
/*==============================================================*/
create table TESTROOM
(
   TESTROOM_ID          int not null auto_increment,
   CODE                 char(20),
   NAME                 char(30),
   DELETE_FLAG          int,
   primary key (TESTROOM_ID)
);

alter table COMMENT add constraint FK_Reference_4 foreign key (COURSE_ID)
      references COURSE (COURSE_ID) on delete restrict on update restrict;

alter table COURSE add constraint FK_Reference_2 foreign key (TESTROOM_ID)
      references TESTROOM (TESTROOM_ID) on delete restrict on update restrict;

alter table COURSE add constraint FK_Reference_3 foreign key (CLASSROOM_ID)
      references CLASSROOM (CLASSROOM_ID) on delete restrict on update restrict;


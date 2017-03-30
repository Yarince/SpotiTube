/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     17/03/2017 15:30:39                          */
/*==============================================================*/


drop table if exists PLAYLIST;

drop table if exists SONG;

drop table if exists TRACK;

drop table if exists TRACK_IN_PLAYLIST;

drop table if exists VIDEO;

/*==============================================================*/
/* Table: PLAYLIST                                              */
/*==============================================================*/
create table PLAYLIST
(
   OWNER                varchar(64) not null,
   NAME                 varchar(64) not null,
   primary key (OWNER, NAME)
);

/*==============================================================*/
/* Table: SONG                                                  */
/*==============================================================*/
create table SONG
(
   PERFORMER            varchar(64) not null,
   TITLE                varchar(64) not null,
   ALBUM                varchar(64) not null,
   primary key (PERFORMER, TITLE, ALBUM)
);

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
create table TRACK
(
   PERFORMER            varchar(64) not null,
   TITLE                varchar(64) not null,
   URL                  varchar(64),
   DURATION             bigint,
   primary key (PERFORMER, TITLE)
);

/*==============================================================*/
/* Table: TRACK_IN_PLAYLIST                                     */
/*==============================================================*/
create table TRACK_IN_PLAYLIST
(
   OWNER                varchar(64) not null,
   NAME                 varchar(64) not null,
   PERFORMER            varchar(64) not null,
   TITLE                varchar(64) not null,
   primary key (OWNER, NAME, PERFORMER, TITLE)
);

/*==============================================================*/
/* Table: VIDEO                                                 */
/*==============================================================*/
create table VIDEO
(
   PERFORMER            varchar(64) not null,
   TITLE                varchar(64) not null,
   PUBLICATIONDATE      datetime not null,
   DECRIPTION           varchar(64) not null,
   PLAYCOUNT            int,
   primary key (PERFORMER, TITLE, PUBLICATIONDATE, DECRIPTION)
);

alter table SONG add constraint FK_SONG_IN_TRACK foreign key (PERFORMER, TITLE)
      references TRACK (PERFORMER, TITLE);

alter table TRACK_IN_PLAYLIST add constraint FK_TRACK_IN_PLAYLIST foreign key (OWNER, NAME)
      references PLAYLIST (OWNER, NAME);

alter table TRACK_IN_PLAYLIST add constraint FK_TRACK_IN_PLAYLIST2 foreign key (PERFORMER, TITLE)
      references TRACK (PERFORMER, TITLE);

alter table VIDEO add constraint FK_VIDEO_IN_TRACK foreign key (PERFORMER, TITLE)
      references TRACK (PERFORMER, TITLE);


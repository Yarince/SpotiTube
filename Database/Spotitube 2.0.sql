/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     22/03/2017 09:23:33                          */
/*==============================================================*/
drop database if exists spotitube;

create database spotitube;

use spotitube;

drop table if exists PLAYLIST;

drop table if exists TRACK;

drop table if exists TRACK_IN_PLAYLIST;

/*==============================================================*/
/* Table: PLAYLIST                                              */
/*==============================================================*/
create table PLAYLIST
(
   PLAYLIST_ID          int not null auto_increment,
   OWNER                varchar(64) not null,
   NAME                 varchar(64) not null,
   primary key (PLAYLIST_ID)
);

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
create table TRACK
(
   TRACK_ID             int not null auto_increment,
   PERFORMER            varchar(64) not null,
   TITLE                varchar(64) not null,
   URL                  varchar(64) not null,
   DURATION             bigint not null,
   OFFLINE_AVAILABLE    bool,
   PLAYCOUNT            int,
   PUBLICATIONDATE      datetime,
   DESCRIPTION           varchar(64),
   ALBUM                varchar(64),
   primary key (TRACK_ID)
);

/*==============================================================*/
/* Table: TRACK_IN_PLAYLIST                                     */
/*==============================================================*/
create table TRACK_IN_PLAYLIST
(
   PLAYLIST_ID          int not null,
   TRACK_ID             int not null,
   primary key (PLAYLIST_ID, TRACK_ID)
);

alter table TRACK_IN_PLAYLIST add constraint FK_TRACK_IN_PLAYLIST foreign key (PLAYLIST_ID)
      references PLAYLIST (PLAYLIST_ID);

alter table TRACK_IN_PLAYLIST add constraint FK_TRACK_IN_PLAYLIST2 foreign key (TRACK_ID)
      references TRACK (TRACK_ID);

  
insert into track (PERFORMER ,TITLE,URL,DURATION,OFFLINE_AVAILABLE,PLAYCOUNT,PUBLICATIONDATE,DESCRIPTION,ALBUM)  values    
				  ('BartTheAmazing','Best Song Ever Vol. 1','/Bart/swek-liedje',153,false,2,CURRENT_DATE,'Mooie video',null),
				  ('BartTheAmazing','Best Song Ever Vol. 2','/Bart/swek-liedje2',154,true,29,CURRENT_DATE,'Geweldigste video',null),
				  ('BartTheAmazing','Best Song Ever Vol. 3','/Bart/swek-liedje3',155,true,143543,CURRENT_DATE,'Beste video',null),
                  ('Yarince','Jam sessie Vol. 1','/Yarince/Jams',323,true,null,null,null,'Jamming Away'),
				  ('Yarince','Jam sessie Vol. 2','/Yarince/Jams',232,false,null,null,null,'Jamming Away'),  
                  ('Yarince','Jam sessie Vol. 3','/Yarince/Jams',423,true,null,null,null,'Jamming Away');
                  
insert into playlistId (OWNER,name) values
							('Yarince','Mooie liedjes'),
							('Bartjeee','Mooiere liedjes');
                            
                            
insert into TRACK_IN_PLAYLIST values 
									 (1,6),
                                     (1,4),
                                     (1,5),
                                     (1,3),
									 (2,1),
									 (2,6),
                                     (2,3),
                                     (2,5);
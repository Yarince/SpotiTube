/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     17/03/2017 15:30:39                          */
/*==============================================================*/

use spotitube;

drop table if exists TRACK_IN_PLAYLIST;
drop table if exists PLAYLIST;
drop table if exists VIDEO;
drop table if exists SONG;
drop table if exists TRACK;
drop table if exists USERS;

delete from playlistId where playlist_Id = 1;

/*==============================================================*/
/* Table: PLAYLIST                                              */
/*==============================================================*/
create table PLAYLIST
(
   PLAYLIST_ID			int not null,
   OWNER                varchar(64) not null,
   NAME                 varchar(64) not null,
   primary key (PLAYLIST_ID)
);

/*==============================================================*/
/* Table: SONG                                                  */
/*==============================================================*/
create table SONG
(
   PERFORMER            varchar(64) not null,
   ALBUM                varchar(64) not null,
   TITLE                varchar(64) not null,
   primary key (PERFORMER, TITLE, ALBUM)
);

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
create table TRACK
(
   PERFORMER            varchar(64) not null,
   TITLE                varchar(64) not null,
   URL                  varchar(64) not null,
   DURATION             bigint 		not null,
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
   PUBLICATIONDATE      datetime 	not null,
   DESCRIPTION          varchar(64) not null,
   PLAYCOUNT            int			not null,
   primary key (PERFORMER, TITLE, PUBLICATIONDATE, DESCRIPTION)
);

/* =============================================================*/
/* Table: USERS
/*==============================================================*/
create table USERS
(
   LOGINNAME         varchar(64) not null,
   PASSWORD           varchar(64) not null,
   FIRSTNAME    	  varchar(64) not null,
   LASTNAME          varchar(64) not null,
   primary key (LOGINNAME)
);

alter table SONG add constraint FK_SONG_IN_TRACK foreign key (PERFORMER, TITLE)
      references TRACK (PERFORMER, TITLE);

alter table TRACK_IN_PLAYLIST add constraint FK_TRACK_IN_PLAYLIST foreign key (OWNER, NAME)
      references PLAYLIST (OWNER, NAME);

alter table TRACK_IN_PLAYLIST add constraint FK_TRACK_IN_PLAYLIST2 foreign key (PERFORMER, TITLE)
      references TRACK (PERFORMER, TITLE);
alter table PLAYLIST add constraint FK_OWNER_OF_PLAYLIST foreign key (OWNER)
      references USERS (LOGINNAME);
alter table VIDEO add constraint FK_VIDEO_IN_TRACK foreign key (PERFORMER, TITLE)
      references TRACK (PERFORMER, TITLE);
	
use spotitube;

insert into users values ('Yarince','droowthcaw','Yarince','Martis'),
					     ('Bartjeee','PASSWORD','Bart','Koelewijn');
                         
insert into track values ('BartTheAmazing','Best Song Ever Vol. 1','/Bart/swek-liedje',153),
						 ('BartTheAmazing','Best Song Ever Vol. 2','/Bart/swek-liedje2',154),
						 ('BartTheAmazing','Best Song Ever Vol. 3','/Bart/swek-liedje3',155),
                         ('Yarince','Jam sessie Vol. 1','/Yarince/Jams',323),
                         ('Yarince','Jam sessie Vol. 2','/Yarince/Jams',232),  
                         ('Yarince','Jam sessie Vol. 3','/Yarince/Jams',423);
                         
insert into video values ('BartTheAmazing','Best Song Ever Vol. 1',CURRENT_DATE,'Geweldige video',1),
						 ('BartTheAmazing','Best Song Ever Vol. 2',CURRENT_DATE,'Geweldigste video',1337),
                         ('BartTheAmazing','Best Song Ever Vol. 3',CURRENT_DATE,'Beste video',3738);

insert into song values	 ('Yarince','Jamming Away','Jam sessie Vol. 1'),
						 ('Yarince','Jamming Away','Jam sessie Vol. 2'),
						 ('Yarince','Jamming Away','Jam sessie Vol. 3');
                         
insert into playlistId values ('Yarince','Mooie liedjes'),
							('Bartjeee','Mooiere liedjes');

insert into TRACK_IN_PLAYLIST values ('Yarince','Mooie liedjes','BartTheAmazing','Best Song Ever Vol. 1'),
									 ('Yarince','Mooie liedjes','BartTheAmazing','Best Song Ever Vol. 2'),
									 ('Yarince','Mooie liedjes','BartTheAmazing','Best Song Ever Vol. 3'),

									 ('Bartjeee','Mooiere liedjes','BartTheAmazing','Best Song Ever Vol. 1'),
									 ('Bartjeee','Mooiere liedjes','BartTheAmazing','Best Song Ever Vol. 2'),
									 ('Bartjeee','Mooiere liedjes','BartTheAmazing','Best Song Ever Vol. 3');
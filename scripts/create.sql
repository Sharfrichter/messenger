create table user
(
	id int auto_increment,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	login varchar(30) not null,
	password varchar(30) not null,

	constraint user_pk primary key (id)
);

create unique index user_login_uindex
	on user (login);

create unique index user_password_uindex
	on user (password);

create table FRIEND
(
    FIRST_PERSON_ID  BIGINT
        constraint FIRST_USER_ID_FK
            references USER,
    SECOND_PERSON_ID BIGINT
        constraint SECOND_USER_ID_FK
            references USER
);

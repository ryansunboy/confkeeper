drop table if exists cross_user_role;
drop table if exists cross_user_auth;
drop table if exists sys_role;
drop table if exists sys_user;
drop table if exists sys_authority;



drop table if exists sys_endpoint_param;
drop table if exists sys_app_endpoint;
drop table if exists sys_app;



create table if not exists sys_user (
  user_id bigint(18) not null auto_increment,
  username varchar(64) not null,
  passwd varchar(256) not null,
  enable int(1) not null default 1,
  creation_time timestamp not null default current_timestamp,
  primary key (user_id)
) engine=innodb auto_increment=1 default charset=utf8;
insert into sys_user values(1, 'admin', 'admin',default, default);



create table if not exists sys_role (
	role_id bigint(18) not null auto_increment,
	role_name varchar(128) not null,
	primary key (role_id)
) engine=innodb default charset=utf8;
insert into sys_role values(1,'ROLE_ADMIN');



create table if not exists sys_authority(
	auth_id bigint(18) not null auto_increment,
	auth_name varchar(128) not null,
	primary key (auth_id)
)engine=innodb default charset=utf8;



create table if not exists cross_user_role(
	user_id bigint(18) not null,
	role_id bigint(18) not null,
	constraint primary key(user_id,role_id),
	constraint fk_cur_uid foreign key (user_id) references sys_user(user_id) on delete cascade on update cascade,
	constraint fk_cur_rid foreign key (role_id) references sys_role(role_id) on delete cascade on update cascade
)engine=innodb default charset=utf8;
insert into cross_user_role value(1,1);



create table if not exists cross_role_auth(
	role_id bigint(18) not null,
	auth_id bigint(18) not null,
	primary key (role_id,auth_id),
	constraint fk_cra_rid foreign key (role_id) references sys_role(role_id) on delete cascade on update cascade,
	constraint fk_cra_authid foreign key (auth_id) references sys_authority(auth_id) on delete cascade on update cascade
)engine=innodb default charset=utf8;





create table if not exists sys_app(
	app_id bigint(18) not null auto_increment,
	app_name varchar(256) not null,
	app_note text,
	primary key (app_id)
)engine=innodb default charset=utf8;

create table if not exists sys_app_endpoint (
	ep_id bigint(18) not null auto_increment,
	app_id bigint(18) not null,
	ep_ver varchar(256) not null default 1,
	ep_state int(1) not null default 0,
	constraint primary key(ep_id),
	constraint fk_app_ep_aid foreign key (app_id) references sys_app(app_id) on delete cascade on update cascade
) engine=innodb default charset=utf8;

create table if not exists sys_endpoint_param (
	param_id bigint(18) not null auto_increment,
	ep_id bigint(18) not null,
	cp_type int not null,
	cp_key varchar(64) not null,
	cp_value text not null,
	constraint pk_ep_param primary key(param_id),
	constraint fk_edparam foreign key (ep_id) references sys_app_endpoint(ep_id) on delete cascade on update cascade
) engine=innodb default charset=utf8;


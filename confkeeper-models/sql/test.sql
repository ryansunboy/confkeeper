
insert into sys_user(user_id,username,passwd) values(2,'test','test');
insert into sys_role values(2,'ROLE_USER');
insert into cross_user_role value(2,2);


insert into sys_app(app_id,app_name,app_note) values(1,'TestApp','For testing');

insert into sys_app_endpoint(ep_id,app_id,ep_ver,ep_state) values(1,1,'TestV1',1);
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(1,1,0,'user_db','mysql');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(2,1,0,'user_db_driver','com.mysql.jdbc.Driver');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(3,1,0,'user_db_uri','jdbc:mysql://127.0.0.1:3306/confsvr?useUnicode=true&amp;characterEncoding=utf8');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(4,1,0,'user_db_username','root');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(5,1,0,'user_db_password','root123');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(6,1,0,'min_connections','5');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(7,1,0,'max_connections','20');

insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(8,1,1,'com.qianwang365.confkeeper.client.utils.TestBean.setTest1(String)','yate');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(8,1,1,'com.qianwang365.confkeeper.client.utils.TestBean.setTest2(long)','111');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(10,1,1,'com.qianwang365.confkeeper.client.utils.TestBean.setTest1(Long)','111');



insert into sys_app_endpoint(ep_id,app_id,ep_ver,ep_state) values(2,1,'TestV2',0);
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(11,2,0,'user_db','mysql');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(12,2,0,'user_db_driver','com.mysql.jdbc.Driver');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(13,2,0,'user_db_uri','jdbc:mysql://127.0.0.1:3306/confsvr?useUnicode=true&amp;characterEncoding=utf8');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(14,2,0,'user_db_username','root');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(15,2,0,'user_db_password','root123');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(16,2,0,'min_connections','5');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(17,2,0,'max_connections','20');

insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(18,2,1,'com.qianwang365.confkeeper.client.utils.TestBean.setTest1(String)','yate_v1');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(19,2,1,'com.qianwang365.confkeeper.client.utils.TestBean.setTest2(long)','222');
insert into sys_endpoint_param(param_id,ep_id,cp_type,cp_key,cp_value) values(20,2,1,'com.qianwang365.confkeeper.client.utils.TestBean.setTest1(Long)','222');


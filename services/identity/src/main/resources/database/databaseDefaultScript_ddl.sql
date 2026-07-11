
insert into tb_role(id,title,description)
values(nextval('role_seq'),'ROLE_Admin','admin role for modify all entity');

insert into tb_role(id,title,description)
values(nextval('role_seq'),'ROLE_User','user role for by modify some of entity');


insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'write','Permission','ALL','Permission Write On Permission Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'read','Permission','ALL','Permission Read On Permission Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'update','Permission','ALL','Permission Update On Permission Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'delete','Permission','ALL','Permission Delete On Permission Table');


insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'write','Role','ALL','Permission Write On Role Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'read','Role','ALL','Permission Read On Role Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'update','Role','ALL','Permission Update On Role Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'delete','Role','ALL','Permission Delete On Role Table');


insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'write','RolePermission','ALL','Permission Write On Role Permission Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'read','RolePermission','ALL','Permission Read On Role Permission Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'update','RolePermission','ALL','Permission Update On Role Permission Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'delete','RolePermission','ALL','Permission Delete On Role Permission Table');


insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'write','User','ALL','Permission for Write On User Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'read','User','ALL','Permission Read On User Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'update','User','ALL','Permission Update On User Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'delete','User','ALL','Permission Delete On User Table');

insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'write','UserRole','ALL','Permission for Write On UserRole Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'read','UserRole','ALL','Permission Read On UserRole Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'update','UserRole','ALL','Permission Update On UserRole Table');
insert into tb_permission(id,operation,target_type,target_scope,title)
values(nextval('prm_seq'),'delete','UserRole','ALL','Permission Delete On UserRole Table');





insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,1);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,2);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,3);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,4);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,5);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,6);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,7);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,8);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,9);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,10);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,11);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,12);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,13);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,14);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,15);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,16);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,17);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,18);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,19);

insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),1,20);


---user
insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),2,13);
insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),2,14);
insert into tb_role_permission(id,fk_role,fk_permission)
values(nextval('role_per_seq'),2,15);


insert into tb_contact_info(id,cell_number,email,phone_number)
values(nextval('cont_seq'),'09359974976','naderaria@gmail.com','09359974976');
insert into tb_location_info(id,city,full_address,house_no)
values(nextval('loc_seq'),'tehran','alvand street','4586-b');

insert into tb_user(id,username,password,account_non_expired,account_non_locked,credentials_non_expired,enabled,fk_location_info,fk_contact_info)
values(nextval('user_seq'),'admin','$2a$10$dueNcpLBCiZlbSFn5WwbZ.gnog6xI1Jyr0sJ7K9wGQpJ3p7M7xLGq',true,true,true,true,1,1);


insert into tb_user_role(id,fk_role,fk_user)
values(nextval('user_role_seq'),1,1);
insert into tb_user_role(id,fk_role,fk_user)
values(nextval('user_role_seq'),2,1);


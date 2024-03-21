drop table if exists  tenants;
drop table if exists  users;

create table tenants (
    id bigint primary key auto_increment,
    organization_name varchar(255),
    requestor_role varchar(255),
    requestor_professional_email varchar(255)
);


create table users (
   id bigint primary key auto_increment,
   username varchar(255),
   name varchar(255),
   password varchar(255),
    tenant_id bigint,
    foreign key (tenant_id) references tenants(id)
);


insert into tenants (organization_name, requestor_role, requestor_professional_email)
 values ('MyOrg', 'HR', 'mounir@hcl.com');


insert into users (username, name, password, tenant_id)
    values ('mounir', 'Mounir', 'password', 1);
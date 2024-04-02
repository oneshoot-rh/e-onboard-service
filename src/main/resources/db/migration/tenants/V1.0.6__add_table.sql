

create table if not exists project_family
(
    id bigint primary key auto_increment,
    name varchar(255) not null,
    description varchar(255) null,
    created_at timestamp default current_timestamp not null
);


alter table projects add column familyId bigint default 0,
    add constraint projects_project_family_id_fk
    foreign key (familyId) references project_family (id);




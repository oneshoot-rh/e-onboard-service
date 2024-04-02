

insert into sites (id, name, address) values (1, 'site1', 'desc');

insert into employees(id,name, employeeCode,email,role,siteId) value (1,'rajan', 'e-7865875T', 'rajan@gmail.com', 'project manager', 1);
insert into employees(id,name, employeeCode,email,role,siteId) value (2,'employee1', 'e-7865875T', 'employee1@gmail.com', 'UI/UX', 1);
insert into employees(id,name, employeeCode,email,role,siteId) value (3,'employee2', 'e-7865875T', 'employee2@gmail.com', 'software engineer', 1);

insert into project_family (id,name, description) values (1,'Client', 'client projects');
insert into project_family (id,name, description) values (2,'Internal', 'internal projects');

insert into projects (id,name, description, projectManagerId,familyId) values (1,'project1', 'project1', 1,1);
insert into projects (id,name, description, projectManagerId,familyId) values (2,'project2', 'project2', 1,1);
insert into projects (id,name, description, projectManagerId,familyId) values (3,'project3', 'project3', 1,1);




insert into assignations (projectId, employeeId, assignationCode, assignationType, initiatedBy, siteId, deployedJobTitle) values (1, 2, 'Y4567', 'NEW', 1, 1, 'UI/UX');
insert into assignations (projectId, employeeId, assignationCode, assignationType, initiatedBy, siteId, deployedJobTitle) values (2, 3, 'Y4567', 'NEW', 1, 1, 'software engineer');
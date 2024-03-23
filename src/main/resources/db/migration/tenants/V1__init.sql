start transaction;


create table if not exists candidates (
    id bigint primary key auto_increment,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    email varchar(255) not null,
    phoneNumber varchar(255) not null,
    createdAt timestamp not null default current_timestamp
);


create table if not exists email_templates (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    subject varchar(255) not null,
    body text not null,
    createdAt timestamp not null default current_timestamp,
    createdBy bigint not null,
    foreign key (createdBy) references eonboardservicedb.users(id)
);

create table if not exists dossier_administratif_submit (
    id varchar(255) primary key,
    isEvaluated boolean not null default false,
    createdAt timestamp not null default current_timestamp,
    candidateId bigint not null,
    foreign key (candidateId) references candidates(id)
);

create table if not exists dossier_administratif_form (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    description varchar(255)  null,
    createdAt timestamp not null default current_timestamp
);

create table if not exists dossier_administrif_requests(
    id bigint auto_increment primary key,
    requestFormId bigint not null,
    createdAt timestamp not null default current_timestamp,
    createdBy bigint not null,
    emailTemplateId bigint not null,
    foreign key (requestFormId) references dossier_administratif_form(id),
    foreign key (emailTemplateId) references email_templates(id),
    foreign key (createdBy) references eonboardservicedb.users(id)
);

create table if not exists dossier_administrif_request_elements (
    id bigint auto_increment primary key,
    elementName varchar(255) not null,
    elementDescription varchar(255)  null,
    isRequired boolean not null default false,
    requestFormId bigint not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (requestFormId) references dossier_administratif_form(id)
);


create table if not exists dossier_administratif_uploads (
    id bigint auto_increment primary key,
    fileName varchar(255) not null,
    path varchar(255) not null,
    fileType varchar(255) null,
    fileSize bigint null,
    dossierAdministratifId varchar(255) not null,
    dossierElementId bigint not null,
    candidateId bigint not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (candidateId) references candidates(id),
    foreign key (dossierAdministratifId) references dossier_administratif_submit(id),
    foreign key (dossierElementId) references dossier_administrif_request_elements(id)
);



create table if not exists candidate_access_account (
    id bigint auto_increment primary key,
    candidateId bigint not null,
    username varchar(255) not null,
    password varchar(255) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    enabled boolean not null default true,
    generatedBy bigint not null,
    foreign key (candidateId) references candidates(id),
    foreign key (generatedBy) references eonboardservicedb.users(id)
);

create table if not exists email_settings (
    id bigint auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    smtpHost varchar(255) not null,
    smtpPort int not null,
    createdAt timestamp not null default current_timestamp,
    createdBy bigint not null,
    foreign key (createdBy) references eonboardservicedb.users(id)
);


create table if not exists sendables (
    id bigint auto_increment primary key,
    type varchar(255)  null,
    name varchar(255)  null,
    description varchar(255)  null,
    path varchar(255) not null,
    fileName varchar(255)  null,
    isGuide boolean not null default false,
    isPolicy boolean not null default false,
    isOther boolean not null default false,
    emailTemplateId bigint null,
    createdAt timestamp not null default current_timestamp,
    createdBy bigint not null,
    foreign key (createdBy) references eonboardservicedb.users(id),
    foreign key (emailTemplateId) references email_templates(id)
);

create table if not exists sendables_candidates (
    id bigint auto_increment primary key,
    sendableId bigint not null,
    candidateId bigint not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (sendableId) references sendables(id),
    foreign key (candidateId) references candidates(id)
);


create table if not exists onboarding_plans (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    description varchar(255)  null,
    flags varchar(255)  null,
    plannedAt timestamp not null,
    emailTemplateId bigint null,
    createdAt timestamp not null default current_timestamp,
    createdBy bigint  null,
    foreign key (emailTemplateId) references email_templates(id),
    foreign key (createdBy) references eonboardservicedb.users(id)
);

create table if not exists onboading_plan_invitees (
    id bigint auto_increment primary key,
    onboardingPlanId bigint not null,
    candidateId bigint not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (onboardingPlanId) references onboarding_plans(id),
    foreign key (candidateId) references candidates(id)
);


create table if not exists sites (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    address varchar(255)  null,
    createdAt timestamp not null default current_timestamp
);

create table if not exists employees(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    employeeCode varchar(255) not null,
    email varchar(255) not null,
    role varchar(255) not null,
    siteId bigint not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (siteId) references sites(id)
);


create table if not exists projects (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    description varchar(255)  null,
    projectManagerId bigint not null,
    status enum('ACTIVE', 'INACTIVE','DELETED') not null default 'ACTIVE',
    createdAt timestamp not null default current_timestamp,
    foreign key (projectManagerId) references employees(id)
);

create table if not exists project_activities (
    id bigint auto_increment primary key,
    projectId bigint not null,
    name varchar(255) not null,
    description varchar(255)  null,
    createdAt timestamp not null default current_timestamp,
    foreign key (projectId) references projects(id)
);



create table if not exists projects_employees (
    id bigint auto_increment primary key,
    projectId bigint not null,
    employeeId bigint not null,
    isActive boolean not null default true,
    createdAt timestamp not null default current_timestamp,
    foreign key (projectId) references projects(id),
    foreign key (employeeId) references eonboardservicedb.users(id)
);


create table if not exists assignations (
    id bigint auto_increment primary key,
    projectId bigint not null,
    employeeId bigint not null,
    assignationCode varchar(255) not null,
    assignationType enum('NEW', 'RENEW') not null,
    assignationStartDate timestamp not null default current_timestamp,
    assignationEndDate timestamp not null default current_timestamp,
    initiatedBy bigint not null,
    siteId bigint not null,
    deployedJobTitle varchar(255) not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (initiatedBy) references eonboardservicedb.users(id),
    foreign key (projectId) references projects(id),
    foreign key (employeeId) references employees(id),
    foreign key (siteId) references sites(id)
);


create table if not exists econtracts (
    id bigint auto_increment primary key,
    path varchar(255) not null,
    fileName varchar(255) not null,
    initiatedBy bigint not null,
    initiatedFor bigint not null,
    isAgreed boolean not null default false,
    expectedStartDate timestamp not null default current_timestamp,
    createdAt timestamp not null default current_timestamp,
    foreign key (initiatedBy) references eonboardservicedb.users(id),
    foreign key (initiatedFor) references candidates(id)
);


create table if not exists timesheets (
    id bigint auto_increment primary key,
    employeeId bigint not null,
    timesheetDate timestamp not null default current_timestamp,
    hoursWorked int not null,
    createdAt timestamp not null default current_timestamp,
    isApproved boolean not null default false,
    approvedBy bigint null,
    foreign key (employeeId) references employees(id),
    foreign key (approvedBy) references employees(id)
);

create table if not exists timesheet_entries (
    id bigint auto_increment primary key,
    timesheetId bigint not null,
    projectId bigint not null,
    activityId bigint not null,
    employeeId bigint not null,
    hoursWorked int not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (timesheetId) references timesheets(id),
    foreign key (projectId) references projects(id),
    foreign key (employeeId) references employees(id),
    foreign key (activityId) references project_activities(id)
);


create table if not exists jobOffers(
    id bigint auto_increment primary key,
    jobTitle varchar(255)  null,
    jobDescription varchar(255)  null,
    jobLocation varchar(255)  null,
    jobType varchar(255)  null,
    jobCategory varchar(255)  null,
    jobStatus varchar(255)  null,
    jobPostedDate timestamp not null default current_timestamp,
    jobClosedDate timestamp  null ,
    jobSalary varchar(255)  null,
    jobExperience varchar(255)  null,
    jobQualification varchar(255) not null,
    jobSkills varchar(255)  null,
    jobResponsibilities varchar(255) not null,
    jobDeadline timestamp  null,
    createdAt timestamp not null default current_timestamp
);

create table if not exists onboarding_plan_job_offers(
    id bigint auto_increment primary key,
    onboardingPlanId bigint not null,
    jobOfferId bigint not null,
    createdAt timestamp not null default current_timestamp,
    foreign key (onboardingPlanId) references onboarding_plans(id),
    foreign key (jobOfferId) references jobOffers(id)
);

insert into candidates (firstName, lastName, email, phoneNumber)
values ('Mounir', 'bakkali', 'bk@gmail.com', '1234567890');


insert into email_templates (name, subject, body, createdBy)
values ('Onboarding Plan Email', 'Onboarding is schelduled', 'dear candidates your onboarding is scheduled', 1);



insert into jobOffers (jobTitle, jobDescription, jobLocation, jobType, jobCategory, jobStatus, jobSalary, jobExperience, jobQualification, jobSkills, jobResponsibilities, jobDeadline)
values ('Software Engineer', 'Software Engineer', 'Casablanca', 'Full Time', 'IT', 'Open', '10000', '2 years', 'Bac+5', 'Java, Spring, Angular', 'Develop software', '2021-12-31');








commit;

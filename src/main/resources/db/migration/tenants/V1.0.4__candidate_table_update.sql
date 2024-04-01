





alter table candidates add column linkedIn varchar(255) null;
alter table candidates add column skills varchar(255) null;
alter table candidates add column experience varchar(255) null;
alter table candidates add column certifications varchar(255) null;
alter table candidates add column score int default 0;
alter table candidates add column status enum('NEW','SELECTED','ONBOARDING','HIRED') default 'NEW';


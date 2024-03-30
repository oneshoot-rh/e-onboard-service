create table if not exists uploads (
    id bigint primary key auto_increment,
    upload_directory varchar(255),
    upload_date_time timestamp,
    uploaded_files int,
    uploaded_by bigint
);

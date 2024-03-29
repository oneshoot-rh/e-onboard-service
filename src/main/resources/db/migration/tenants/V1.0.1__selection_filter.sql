



create table if not exists selection_filters
(
    id     bigint primary key auto_increment,
    filterKey varchar(255) not null,
    filterValue varchar(255) not null,
    processId varchar(255) not null
);
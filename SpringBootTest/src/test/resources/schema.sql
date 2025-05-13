drop table discount if exists;
create table discount (
    id identity primary key,
    type varchar(255),
    value1 double precision
);
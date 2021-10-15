create table hibernate_sequence (
    next_val bigint
) engine = InnoDB;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table orders
(
    id          bigint not null auto_increment,
    address     varchar(255),
    description varchar(255),
    user_id     bigint,
    primary key (id)
) engine = InnoDB;
create table passports
(
    id     bigint       not null auto_increment,
    number varchar(255) not null,
    primary key (id)
) engine = InnoDB;
create table project
(
    project_id bigint      not null auto_increment,
    title      varchar(255) not null,
    primary key (project_id)
) engine = InnoDB;
create table user
(
    id          bigint           not null auto_increment,
    name        varchar(255),
    password    varchar(255),
    salary      double precision not null,
    passport_id bigint,
    primary key (id)
) engine = InnoDB;
create table users_project
(
    id         bigint  not null,
    project_id bigint not null,
    primary key (id, project_id)
) engine = InnoDB;
alter table passports
    add constraint UK_31qkfr6f1adbr1qyyaohugv0 unique (number);
alter table orders
    add constraint FKel9kyl84ego2otj2accfd8mr7 foreign key (user_id) references user (id);
alter table user
    add constraint FKn74nrom8ib69h5smov09uaet5 foreign key (passport_id) references passports (id);
alter table users_project
    add constraint FKnns300cdahrg4ye5xj82s6ltc foreign key (project_id) references project (project_id);
alter table users_project
    add constraint FK30tvybsxfppo3nch46wwvch5l foreign key (id) references user (id);


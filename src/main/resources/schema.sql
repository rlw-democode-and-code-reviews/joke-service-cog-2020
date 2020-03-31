drop table if exists jokes;
create table `jokes`
(
    joke_id bigint auto_increment,
    `category` VARCHAR(50) not null,
    `joke`     VARCHAR(500) not null,
    constraint jokes_pk
        primary key (joke_id)
);
create table if not exists music (
    id integer not null auto_increment,
    title varchar(50) not null,
    artist_id integer not null,
    primary key (id),
    foreign key (artist_id) references artist(id)
);
create table IF NOT EXISTS book
(
    id          INT          NOT NULL PRIMARY KEY,
    title       VARCHAR(150) NOT NULL,
    author      VARCHAR(150) NOT NULL,
    description VARCHAR(150)
);

insert into book (id, title, author, description)
values (1, 'Crime and Punishment', 'F. Dostoevsky', null);

insert into book (id, title, author, description)
values (2, 'Anna Karenina', 'L. Tolstoy', null);

insert into book (id, title, author, description)
values (3, 'The Brothers Karamazov', 'F. Dostoevsky', null);

insert into book (id, title, author, description)
values (4, 'War and Peace', 'L. Tolstoy', null);

insert into book (id, title, author, description)
values (5, 'Dead Souls', 'N. Gogol', null);
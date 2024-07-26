create table if not exists students(id bigserial primary key, name varchar(255), score int);

insert into students (name, score) VALUES
                                       ('Bob', 100),
                                       ('John', 100),
                                       ('Alex', 100);
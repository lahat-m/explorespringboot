create sequence user_id_seq start with 1 increment by 1;
create table users (
                       id bigint not null default nextval('user_id_seq'),
                       email varchar(255) not null unique,
                       password varchar(255) not null,
                       name varchar(255) not null,
                       role varchar(20) not null,
                       created_at timestamp not null,
                       updated_at timestamp,
                       primary key (id),
                       constraint user_email_unique unique (email)
);

create sequence post_id_seq start with 1 increment by 1;

create table posts (
                       id bigint not null default nextval('post_id_seq'),
                       title varchar(250) not null,
                       slug varchar(250) not null unique,
                       content text not null,

                       created_by bigint not null,
                       user_id bigint not null,

                       created_at timestamp not null,
                       updated_at timestamp,

                       primary key (id),

                       constraint post_created_by_fk foreign key (created_by) references users(id)
);

create sequence comment_id_seq start with 1 increment by 1;

create table comments(
                         id bigint not null default nextval('comment_id_seq'),

                         name varchar(250) not null,
                         email varchar(250) not null,
                         content text not null,

                         created_at timestamp not null,
                         updated_at timestamp,
                         post_id bigint not null,

                         primary key (id),
                         constraint comment_post_id_fk foreign key (post_id) references posts(id)
)
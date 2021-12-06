CREATE USER test WITH password 'password';
CREATE DATABASE test;
GRANT ALL PRIVILEGES ON DATABASE test TO test;

\connect test test;

create schema IF NOT EXISTS TEST_SCHEMA AUTHORIZATION test;

show search_path;
SET search_path TO TEST_SCHEMA;

create table IF NOT EXISTS MODEL (
    id SERIAL PRIMARY key,
    random varchar(255) not null,
    is_updated varchar(255) not null
    );
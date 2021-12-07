create schema IF NOT EXISTS TEST_SCHEMA;

show search_path;
SET search_path TO TEST_SCHEMA;

create table IF NOT EXISTS MODEL (
    id SERIAL PRIMARY key,
    random varchar(255) not null,
    is_updated varchar(255) not null
    );
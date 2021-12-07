create schema IF NOT EXISTS TEST_SCHEMA;

show search_path;
SET search_path TO TEST_SCHEMA;

create table IF NOT EXISTS MODEL (
    id SERIAL PRIMARY key,
    random varchar(255)
    );

create table IF NOT EXISTS SECOND_MODEL (
    value varchar(255) PRIMARY key
    );
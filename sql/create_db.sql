CREATE DATABASE easylearning_db WITH ENCODING UTF8;

CREATE ROLE easylearning_application;

CREATE SCHEMA easylearning AUTHORIZATION easylearning_application;

GRANT SELECT,INSERT,UPDATE,DELETE
ON ALL TABLES IN SCHEMA easylearning
TO easylearning_application;


CREATE DATABASE easylearning_db WITH ENCODING UTF8;

CREATE ROLE easylearning_application WITH LOGIN PASSWORD '1234';

\connect easylearning_db;
GRANT USAGE 
ON SCHEMA public 
TO easylearning_application;

GRANT ALL PRIVILEGES
ON ALL TABLES IN SCHEMA public
TO easylearning_application;

GRANT ALL PRIVILEGES
ON ALL SEQUENCES IN SCHEMA public
TO easylearning_application;


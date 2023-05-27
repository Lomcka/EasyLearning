CREATE DATABASE easylearning_test_db WITH ENCODING UTF8;

CREATE ROLE easylearning_test_app WITH LOGIN PASSWORD '1234';

\connect easylearning_test_db;
GRANT USAGE 
ON SCHEMA public 
TO easylearning_test_app;

GRANT ALL PRIVILEGES
ON ALL TABLES IN SCHEMA public
TO easylearning_test_app;

GRANT ALL PRIVILEGES
ON ALL SEQUENCES IN SCHEMA public
TO easylearning_test_app;



CREATE USER 'netflix'@'localhost' IDENTIFIED BY 'nadmin';

CREATE DATABASE netflix;

GRANT ALL PRIVILEGES ON netflix.* TO 'netflix'@'localhost';

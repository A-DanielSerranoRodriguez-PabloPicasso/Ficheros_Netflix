use netflix;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  mail VARCHAR(50) NOT NULL,
  passwd TEXT NOT NULL,
  active TINYINT(1) NOT NULL,
  activation_code INT,
  separator_char CHAR(1)
);

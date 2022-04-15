USE netflix;

DROP TABLE IF EXISTS shows;

CREATE TABLE shows(
  id INT PRIMARY KEY,
  type TEXT,
  title TEXT,
  director TEXT,
  cast TEXT,
  country TEXT,
  date_added TEXT,
  date_release INT,
  rating TEXT,
  duration TEXT,
  listed_in TEXT,
  description TEXT
);

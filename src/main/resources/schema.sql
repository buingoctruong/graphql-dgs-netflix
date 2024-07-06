--
-- Delete the tables
--
-- Currently, due to FOREIGN KEY dependencies, the deletion order is strict,
-- A better approach is to drop all FOREIGN KEYs before the tables.
--

DROP TABLE IF EXISTS quote;
DROP TABLE IF EXISTS author;


--
-- Table structure for table `author`
--

CREATE TABLE author (
  id INT AUTO_INCREMENT PRIMARY KEY,
  birthday VARCHAR(255),
  image VARCHAR(255),
  link VARCHAR(255),
  name VARCHAR(255) NOT NULL,
  profile TEXT
);

CREATE TABLE quote (
  id INT PRIMARY KEY,
  content TEXT,
  author_id INT,
  CONSTRAINT FK_author FOREIGN KEY (author_id) REFERENCES author(id)
);

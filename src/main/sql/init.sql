DROP TABLE IF EXISTS responses;
DROP TABLE IF EXISTS subtasks CASCADE;
DROP TABLE IF EXISTS ranged CASCADE;
DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS types CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS estimates CASCADE;
DROP TABLE IF EXISTS binaryaccuracies CASCADE;
DROP TABLE IF EXISTS continuousaccuracies CASCADE;
DROP TABLE IF EXISTS multivalueaccuracies CASCADE;
DROP TABLE IF EXISTS experts CASCADE;
DROP TABLE IF EXISTS bots CASCADE;
DROP TABLE IF EXISTS annotation_types;
DROP TABLE IF EXISTS media_types;
DROP TABLE IF EXISTS input_types;

CREATE TABLE accounts
(
  id SERIAL PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  username VARCHAR(12) NOT NULL,
  password VARCHAR(100) NOT NULL,
  type BOOLEAN NOT NULL,
  session CHARACTER(32) NULL,
  last_active TIMESTAMP NULL,
  accuracy INTEGER
);

CREATE TABLE media_types
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(32)
);

CREATE TABLE annotation_types
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(32)
);

CREATE TABLE input_types
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(32)
);

CREATE TABLE tasks
(
  id SERIAL PRIMARY KEY,
  submitter INTEGER REFERENCES accounts (id),
  name VARCHAR(100) NOT NULL,
  question VARCHAR(100) NOT NULL,
  accuracy FLOAT NOT NULL,
  media_type INTEGER REFERENCES media_types (id),
  annotation_type INTEGER REFERENCES annotation_types (id),
  input_type INTEGER REFERENCES input_types (id),
  answers VARCHAR(256),
  max_labels INTEGER NOT NULL,
  ex_time TIMESTAMP,
  date_created TIMESTAMP
);

CREATE TABLE subtasks
(
  id SERIAL PRIMARY KEY,
  task INTEGER REFERENCES tasks (id),
  file_name VARCHAR(32) NOT NULL,
  active BOOLEAN
);

CREATE TABLE estimates
(
  estimate_key SERIAL PRIMARY KEY,
  subtask_id INTEGER REFERENCES subtasks (id),
  estimate VARCHAR(32),
  confidence FLOAT
);

CREATE TABLE responses
(
  id SERIAL PRIMARY KEY,
  account INTEGER REFERENCES accounts (id),
  subtask INTEGER REFERENCES subtasks (id),
  response VARCHAR(32) NULL
);

CREATE TABLE ranged
(
  subtask INTEGER PRIMARY KEY,
  start INTEGER,
  finish INTEGER,
  p DOUBLE PRECISION
);

CREATE TABLE binaryaccuracies
(
	account INTEGER REFERENCES accounts (id),
	truePositive DOUBLE PRECISION NOT NULL,
	trueNegative DOUBLE PRECISION NOT NULL,
	numPositive INTEGER NOT NULL,
	numNegative INTEGER NOT NULL	
);

CREATE TABLE continuousaccuracies
(
	account INTEGER REFERENCES accounts (id),
	accuracy DOUBLE PRECISION NOT NULL
);

CREATE TABLE multivalueaccuracies
(
	account INTEGER REFERENCES accounts (id),
	accuracy DOUBLE PRECISION NOT NULL
);

CREATE TABLE experts
(
	entry_id SERIAL PRIMARY KEY,
	account INTEGER REFERENCES accounts (id),
	type SMALLINT NOT NULL
);

CREATE TABLE bots
(
	entry_id SERIAL PRIMARY KEY,
	account INTEGER REFERENCES accounts (id),
	type SMALLINT NOT NULL
);

INSERT INTO media_types
VALUES(1, 'image');
INSERT INTO media_types
VALUES(2, 'audio');
INSERT INTO media_types
VALUES(3, 'text');


INSERT INTO annotation_types
VALUES(1, 'binary');
INSERT INTO annotation_types
VALUES(2, 'category');
INSERT INTO annotation_types
VALUES(3, 'ncontinuous');

INSERT INTO input_types
VALUES(1, 'radio');
INSERT INTO input_types
VALUES(2, 'slider');
INSERT INTO input_types
VALUES(3, 'coordinate');
INSERT INTO input_types
VALUES(4, 'bounding');

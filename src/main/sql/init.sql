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

CREATE TABLE types
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE tasks
(
  id SERIAL PRIMARY KEY,
  submitter INTEGER REFERENCES accounts (id),
  name VARCHAR(100) NOT NULL,
  question VARCHAR(100) NOT NULL,
  accuracy INTEGER NOT NULL,
  type INTEGER REFERENCES types (id),
  ex_time TIMESTAMP,
  max_labels INTEGER NOT NULL,
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
  estimate BIT VARYING,
  confidence FLOAT
);

CREATE TABLE responses
(
  id SERIAL PRIMARY KEY,
  account INTEGER REFERENCES accounts (id),
  subtask INTEGER REFERENCES subtasks (id),
  response BIT VARYING NULL
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
	account INTEGER REFERENCES accounts (id),
	type SMALLINT NOT NULL
);

CREATE TABLE bots
(
	account INTEGER REFERENCES accounts (id),
	type SMALLINT NOT NULL
);

INSERT INTO types
VALUES(1, 'binary');

INSERT INTO types
VALUES(2, 'continuous');

INSERT INTO types
VALUES(3, 'multivalue');

--INSERT INTO accounts
--VALUES(1,'adam','adam', NULL, NULL, NULL, NULL, true, 1);

--INSERT INTO tasks
--VALUES( 1,1,'test', 'test',10,1,NULL,NULL);


DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS subtasks;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS types;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
  id SERIAL PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  username VARCHAR(12) NOT NULL,
  password BIT(256) NOT NULL,
  type BIT(3) NOT NULL,
  session CHARACTER(32) NULL,
  last_active TIMESTAMP NOT NULL,
  expert BOOLEAN,
  accuracy INTEGER
);

CREATE TABLE media_handlers
(
  id SERIAL PRIMARY KEY,
  description TEXT NOT NULL,
  class VARCHAR(100) NOT NULL
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
  handler INTEGER REFERENCES media_handlers (id),
  ex_time INTEGER
);

CREATE TABLE subtasks
(
  id SERIAL PRIMARY KEY,
  task INTEGER REFERENCES tasks (id),
  media BIT VARYING NULL,
  estimate BIT VARYING NULL,
  confidence FLOAT,
  active BOOLEAN
);

CREATE TABLE responses
(
  id SERIAL PRIMARY KEY,
  account INTEGER REFERENCES accounts (id),
  subtask INTEGER REFERENCES subtasks (id),
  response BIT VARYING NULL
);

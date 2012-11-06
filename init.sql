
DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS subtasks;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
  id SERIAL PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  username VARCHAR(12) NOT NULL,
  password BIT(256) NOT NULL,
  type BIT(3) NOT NULL,
  session CHARACTER(32),
  last_active TIMESTAMP NOT NULL
);

CREATE TABLE tasks
(
  id SERIAL PRIMARY KEY,
  submitter INTEGER REFERENCES accounts (id),
  name VARCHAR(100) NOT NULL,
  question VARCHAR(100) NOT NULL
);

CREATE TABLE subtasks
(
  id SERIAL PRIMARY KEY,
  task INTEGER REFERENCES tasks (id),
  classification BIT NULL
);

CREATE TABLE assignments
(
  id SERIAL PRIMARY KEY,
  account INTEGER REFERENCES accounts (id),
  subtask INTEGER REFERENCES subtasks (id)
);

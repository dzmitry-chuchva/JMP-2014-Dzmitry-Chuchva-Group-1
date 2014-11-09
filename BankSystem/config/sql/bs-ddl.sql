DROP TABLE IF EXISTS banks;
DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS currencies;

CREATE TABLE banks (
  bank_id BIGSERIAL NOT NULL,
  name VARCHAR(128) NOT NULL,
  deleted SMALLINT NOT NULL DEFAULT 0,
  CONSTRAINT pk_bank_id PRIMARY KEY (bank_id)
);

CREATE TABLE persons (
  person_id BIGSERIAL NOT NULL,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
  deleted SMALLINT NOT NULL DEFAULT 0,
  CONSTRAINT pk_person_id PRIMARY KEY (person_id)
);

CREATE TABLE currencies (
  currency_id BIGSERIAL NOT NULL,
  code VARCHAR(3) NOT NULL,
  full_name VARCHAR(64) NOT NULL,
  deleted SMALLINT NOT NULL DEFAULT 0,
  CONSTRAINT pk_currency_id PRIMARY KEY (currency_id)
);
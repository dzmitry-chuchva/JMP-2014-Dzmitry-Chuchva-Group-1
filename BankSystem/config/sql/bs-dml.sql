START TRANSACTION;

INSERT INTO banks (bank_id, name, deleted) VALUES (1, 'Belarusbank', 0);

INSERT INTO persons (person_id, first_name, last_name, deleted) VALUES (1, 'Petr', 'Petrov', 0);
INSERT INTO persons (person_id, first_name, last_name, deleted) VALUES (2, 'Ivan', 'Ivanov', 0);
INSERT INTO persons (person_id, first_name, last_name, deleted) VALUES (3, 'Boris', 'Borisov', 0);

INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (1, 'BYR', 'Belarusian ruble', 0);
INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (2, 'RUB', 'Russian ruble', 0);
INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (3, 'USD', 'United States dollar', 0);
INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (4, 'EUR', 'Euro', 0);

INSERT INTO accounts (account_id, bank_id, person_id, currency_id, total_cash, deleted) VALUES (1, 1, 1, 1, 15000000, 0);
INSERT INTO accounts (account_id, bank_id, person_id, currency_id, total_cash, deleted) VALUES (2, 1, 2, 2, 12000, 0);
INSERT INTO accounts (account_id, bank_id, person_id, currency_id, total_cash, deleted) VALUES (3, 1, 3, 3, 1600, 0);

COMMIT TRANSACTION;
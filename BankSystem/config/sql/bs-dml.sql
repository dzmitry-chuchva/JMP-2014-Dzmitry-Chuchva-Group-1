START TRANSACTION;

INSERT INTO banks (bank_id, name, deleted) VALUES (1, 'Belarusbank', 0);

INSERT INTO persons (person_id, first_name, last_name, deleted) VALUES (1, 'Petr', 'Petrov', 0);
INSERT INTO persons (person_id, first_name, last_name, deleted) VALUES (2, 'Ivan', 'Ivanov', 0);
INSERT INTO persons (person_id, first_name, last_name, deleted) VALUES (3, 'Boris', 'Borisov', 0);

INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (1, 'BYR', 'Belarusian ruble', 0);
INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (2, 'RUB', 'Russian ruble', 0);
INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (3, 'USD', 'United States dollar', 0);
INSERT INTO currencies (currency_id, code, full_name, deleted) VALUES (4, 'EUR', 'Euro', 0);

COMMIT TRANSACTION;
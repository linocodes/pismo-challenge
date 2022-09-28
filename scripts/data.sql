INSERT INTO account(account_id, document_number) VALUES(1,'12345678900');
INSERT INTO account(account_id, document_number) VALUES(2,'45644544545');
INSERT INTO account(account_id, document_number) VALUES(3,'54544545450');
INSERT INTO account(account_id, document_number) VALUES(4,'45454545440');

INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (1,400.50,4, now());
INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (1,-300.0,1, now());
INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (1,100.0,4, now());
INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (3,4500.0,4, now());
INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (1,-300.0,2, now());
INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (1,-300.0,3, now());
INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (2,6780.0,4, now());
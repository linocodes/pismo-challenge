INSERT INTO account(account_id, document_number, available_credit_limit) VALUES(1,'12345678900',200);
INSERT INTO account(account_id, document_number) VALUES(2,'45644544545');
INSERT INTO account(account_id, document_number) VALUES(3,'54544545450');
INSERT INTO account(account_id, document_number) VALUES(4,'45454545440');

INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (1,100,4, now());
INSERT INTO transaction(account_id, amount, type_operation, event_date) VALUES (1,100,4, now());

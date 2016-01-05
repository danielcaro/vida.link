-- // create changelog table

CREATE TABLE auth_tokens(
id varchar PRIMARY KEY,
detail varchar
);

INSERT INTO auth_tokens(id) VALUES('login');
INSERT INTO auth_tokens(id) VALUES('mac');
INSERT INTO auth_tokens(id) VALUES('uuid');



-- //@UNDO
DROP TABLE IF EXISTS auth_tokens  CASCADE; 
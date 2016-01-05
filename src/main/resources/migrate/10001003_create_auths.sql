-- // create changelog table

CREATE TABLE auths(
peer_id  		integer REFERENCES peers(id),	 -- cambiar a ID
auth_token              varchar REFERENCES auth_tokens(id),
auth_id                 varchar,
auth_key                varchar, -- la clave
enable                  boolean
);

-- sólo un método de logeo, único
ALTER TABLE auths ADD PRIMARY KEY(auth_token, auth_id); 

INSERT INTO auths (peer_id, auth_token, auth_id, auth_key) VALUES(0,'login', 'app1@vida.link', 'manager');
INSERT INTO auths (peer_id, auth_token, auth_id, auth_key) VALUES(1,'login', 'app2@vida.link', 'manager');



-- //@UNDO
DROP TABLE IF EXISTS auths  CASCADE;
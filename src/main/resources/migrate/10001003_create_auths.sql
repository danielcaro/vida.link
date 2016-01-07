-- // create changelog table

CREATE TABLE auths(
peer_id  		integer REFERENCES peers(id),	 -- cambiar a ID
auth_token              varchar REFERENCES auth_tokens(id),
auth_id                 varchar,
auth_key                text, -- la clave
enable                  boolean
);

-- sólo un método de logeo, único
ALTER TABLE auths ADD PRIMARY KEY(auth_token, auth_id); 

INSERT INTO auths (peer_id, auth_token, auth_id, auth_key) VALUES(0,'login', 'admin@vida.link', md5('vdl'));
INSERT INTO auths (peer_id, auth_token, auth_id, auth_key) VALUES(1,'login', 'manager@vida.link', md5('vdl'));



-- //@UNDO
DROP TABLE IF EXISTS auths  CASCADE;
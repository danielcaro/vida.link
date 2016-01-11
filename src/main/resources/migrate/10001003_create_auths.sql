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
-- https://www.packtpub.com/packtlib/book/Application%20Development/9781849697767/1/ch01lvl1sec10/Adding%20salt%20to%20a%20hash%20(Intermediate)
INSERT INTO auths (peer_id, auth_token, auth_id, auth_key) VALUES(0,'login', 'admin@vida.link', '$2a$11$P97KizrpMO3XQG1Kq6HzWuvD/ZEnsrD5kKUsqHe3yhufm8irzQcha');
INSERT INTO auths (peer_id, auth_token, auth_id, auth_key) VALUES(1,'login', 'manager@vida.link', '$2a$11$P97KizrpMO3XQG1Kq6HzWuvD/ZEnsrD5kKUsqHe3yhufm8irzQcha');

-- Determinar el valor de la clave codificada 
-- para el usuario por defecto


-- //@UNDO
DROP TABLE IF EXISTS auths  CASCADE;
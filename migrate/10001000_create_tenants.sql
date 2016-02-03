-- // create changelog table

CREATE TABLE tenants(
id			serial PRIMARY KEY,	 -- cambiar a ID
symbol   		varchar,
enable                  boolean
);
INSERT INTO tenants(id,symbol,enable) VALUES(0,'vdw', true);


-- //@UNDO
DROP TABLE IF EXISTS tenants CASCADE; --- CATALOGO
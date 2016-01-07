-- // create changelog table
CREATE TABLE peers (
      ID bigint NOT NULL,
      PEER_NAME VARCHAR(50) NOT NULL,
      DESCRIPTION text
); 

ALTER TABLE peers 
ADD CONSTRAINT PK_peers 
PRIMARY KEY (id);

INSERT INTO peers(id, peer_name) VALUES(0,'ADMIN');
INSERT INTO peers(id, peer_name) VALUES(1,'MANAGER');

INSERT INTO peers(id, peer_name) VALUES(2,'XEIN-THINKPAD');
INSERT INTO peers(id, peer_name) VALUES(3,'XEIN-RASPBERRY');



-- //@UNDO
DROP TABLE peers ;
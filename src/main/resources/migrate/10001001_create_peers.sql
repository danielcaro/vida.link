-- // create changelog table
CREATE TABLE peers (
      ID bigint NOT NULL,
      PEER_NAME VARCHAR(50) NOT NULL,
      DESCRIPTION text NOT NULL
); 

ALTER TABLE peers 
ADD CONSTRAINT PK_peers 
PRIMARY KEY (id);



-- //@UNDO
DROP TABLE peers ;
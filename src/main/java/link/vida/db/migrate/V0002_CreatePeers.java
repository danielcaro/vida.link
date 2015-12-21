/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db.migrate;

import java.math.BigDecimal;
import org.apache.ibatis.migration.MigrationScript;

public class V0002_CreatePeers implements MigrationScript {
    
  @Override
  public BigDecimal getId() {
     return BigDecimal.valueOf(Long.parseLong(this.getClass().getSimpleName().replace("V", "").split("_")[0]));  
  }

  @Override
  public String getDescription() {
    return this.getClass().getSimpleName().split("_")[1];
  }

  @Override
  public String getUpScript() {
    return "CREATE TABLE peers ("
      + "ID NUMERIC(20,0) NOT NULL,"
      + "APPLIED_AT VARCHAR(25) NOT NULL,"
      + "DESCRIPTION VARCHAR(255) NOT NULL); "

      + "ALTER TABLE peers "
      + "ADD CONSTRAINT PK_peers "
      + "PRIMARY KEY (id);";
  }

  @Override
  public String getDownScript() {
    return "DROP TABLE peers;";
  }
}
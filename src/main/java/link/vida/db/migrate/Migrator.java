/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db.migrate;

import java.io.PrintStream;
import link.vida.gw.LoggingOutputStream;
import org.apache.ibatis.migration.JavaMigrationLoader;
import org.apache.ibatis.migration.JdbcConnectionProvider;
import org.apache.ibatis.migration.operations.StatusOperation;
import org.apache.ibatis.migration.operations.UpOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class Migrator {

    private static final Logger log = LoggerFactory.getLogger(Migrator.class);
    protected static final String LINE_SEPERATOR = System.getProperty("line.separator");

    //http://stackoverflow.com/questions/11187461/redirect-system-out-and-system-err-to-slf4j
    
    public void run() throws Exception {
        PrintStream ps =  new PrintStream(new LoggingOutputStream(log, false),true);
        new StatusOperation().operate(
                new JdbcConnectionProvider("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/vdl", "vdl", "vdl"),
                new JavaMigrationLoader("link.vida.db.migrate"), null, ps);
        log.info("");
        new UpOperation().operate(
                new JdbcConnectionProvider("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/vdl", "vdl", "vdl"),
                new JavaMigrationLoader("link.vida.db.migrate"), null, ps);
        log.info("");
        new StatusOperation().operate(
                new JdbcConnectionProvider("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/vdl", "vdl", "vdl"),
                new JavaMigrationLoader("link.vida.db.migrate"), null, ps);
    }

}

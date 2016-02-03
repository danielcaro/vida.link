/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db;

import java.io.File;
import java.io.PrintStream;
import java.util.Properties;
import link.vida.utils.LoggingOutputStream;
import link.vida.utils.FileConfig;
import org.apache.ibatis.migration.FileMigrationLoader;
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

    //http://stackoverflow.com/questions/11187461/redirect-system-out-and-system-err-to-slf4j
    public void run() throws Exception {
        PrintStream ps = new PrintStream(new LoggingOutputStream(log, false), true);

        String path = "migrate";
        // this.getClass().getClassLoader().getResource("migrate").getPath();
        // scriptsDir is the only required parameter and it indicates the directory 
        //containing the migration scripts. Note that the directory must exist 
        // in the file system of the runtime environment.
        // en caso contrario hay que usar Java ...
        
        log.info("MIGRATE PATH :" + path);

        final File folder = new File(path);
        
        log.info("IS DIRECTORY:" + folder.isDirectory());

        final Properties prop = FileConfig.
                getPropertiesFromFile("db.migrate.properties");

        JdbcConnectionProvider jdbcP = new JdbcConnectionProvider(
                prop.getProperty("driver"),
                prop.getProperty("url"),
                prop.getProperty("username"),
                prop.getProperty("password"));

        FileMigrationLoader fmLoader = new FileMigrationLoader(folder, null, prop);

        new StatusOperation().operate(jdbcP, fmLoader, null, ps);

        new UpOperation().operate(jdbcP, fmLoader, null, ps);

        new StatusOperation().operate(jdbcP, fmLoader, null, ps);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db;

import link.vida.db.vdl.VdlDao;
import com.google.inject.PrivateModule;
import com.google.inject.name.Names;
import link.vida.utils.FileConfig;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

/**
 *
 * @author dcaro
 */
public class ConfigDB extends PrivateModule {

    @Override
    protected void configure() {
        install(new MyBatisModule() {

            @Override
            protected void initialize() {
                bindDataSourceProviderType(PooledDataSourceProvider.class);
                bindTransactionFactoryType(JdbcTransactionFactory.class);
                addMapperClasses("link.vida.db.vdl.mappers");
                aggressiveLazyLoading(false);
                lazyLoadingEnabled(true);
                multipleResultSetsEnabled(true);
                useGeneratedKeys(true);
                useCacheEnabled(true);
                environmentId("vdl");
            }
        });
        Names.bindProperties(binder(), FileConfig.getPropertiesFromFile("db.vdl.properties"));
        bind(VdlDao.class);
        expose(VdlDao.class);
    }

}

package lk.ijse.dep8.note.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JPAConfig {


    private final Environment env;

    public JPAConfig(Environment env) {
        this.env = env;
    }

    public static PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactory(DataSource ds, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setDataSource(ds);
        lcemf.setJpaVendorAdapter(jpaVendorAdapter);
        lcemf.setPackagesToScan("lk.ijse.dep8.note.entity");
        return lcemf;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(env.getProperty("jpa.generate-ddl", Boolean.class, false));
//        jpaVendorAdapter.setDatabase(Database.MYSQL);    //not compulsory to mention this
        jpaVendorAdapter.setShowSql(env.getProperty("jpa.show-sql", Boolean.class, false));
        jpaVendorAdapter.setDatabasePlatform(env.getRequiredProperty("jpa.dialect"));
        return jpaVendorAdapter;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(env.getRequiredProperty("hikari.jdbc-url"));
        hikariDataSource.setUsername(env.getRequiredProperty("hikari.username"));
        hikariDataSource.setPassword(env.getRequiredProperty("hikari.password"));
        hikariDataSource.setDriverClassName(env.getRequiredProperty("hikari.driver-classname"));
        hikariDataSource.setMaximumPoolSize(env.getRequiredProperty("hikari.max-pool-size", Integer.class));
        return hikariDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}

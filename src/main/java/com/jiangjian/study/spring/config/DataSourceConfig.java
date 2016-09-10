package com.jiangjian.study.spring.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.jiangjian.study.spring.persistence")
public class DataSourceConfig {
    @Bean
    @Profile("prod")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/COREJAVA");
        ds.setUsername("root");
        ds.setPassword("jiangjian");
        return ds;
    }

    @Bean
    @Profile("qa")
    public DataSource Data() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:tcp://localhost/~/COREJAVA");
        ds.setUsername("jiangjian");
        ds.setPassword("");
        ds.setInitialSize(5);
        ds.setMaxActive(5);
        return ds;
    }

    @Bean
    @Profile("dev")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }

    @Bean JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    /**
     * 注意这里EntityManagerFactory方法的名称是必须是entityManagerFactory,因为Spring Data默认会找名为"entityManagerFactory"的Bean
     * 你可以显示指定EntityManagerFactory的名字，请参考:http://stackoverflow.com/questions/24520602/spring-data-jpa-no-bean-named-entitymanagerfactory-is-defined-injection-of-a
     * @param dataSource
     * @param jpaVendorAdapter
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.jiangjian.study.spring.domain");
        entityManagerFactoryBean.setPersistenceUnitName("jiangjian");
        return entityManagerFactoryBean;
    }

    @Configuration
    @EnableTransactionManagement
    public static class TransactionConfig implements TransactionManagementConfigurer {
        @Autowired
        private EntityManagerFactory emf;

        public PlatformTransactionManager annotationDrivenTransactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(emf);
            return transactionManager;
        }
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post-processor that adds
     * an adviser to any bean that’s annotated with @Repository so that any platform-specific
     * exceptions are caught and then rethrown as one of Spring’s unchecked data-access
     * exceptions.
     * @return
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

/*如果你想使用Spring JDBC去操作数据库，请使用如下配置
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{"com.jiangjian.study.spring.model"});
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

*/

//如果Hibernate 4以下版本，Mapping是通过注解定义的，可以通过如下方式定义hibernate 的sessionFactory
//    @Bean
//    public AnnotationSessionFactoryBean sessionFactory(DataSource ds) {
//        AnnotationSessionFactoryBean sfb = new AnnotationSessionFactoryBean();
//        sfb.setDataSource(ds);
//        sfb.setPackagesToScan(new String[] { "com.habuma.spittr.domain" });
//        Properties props = new Properties();
//        props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
//        sfb.setHibernateProperties(props);
//        return sfb;
//    }

//    //如果Hibernate 4以下版本，Mapping是通过xml定义的，可以通过如下方式定义hibernate 的sessionFactory
//    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
//        sfb.setDataSource(dataSource);
//        sfb.setMappingResources(new String[]{"Spitter.hbm.xml"});
//        Properties props = new Properties();
//        props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
//        sfb.setHibernateProperties(props);
//        return sfb;
//    }

}

package com.ats.appconfig;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.ats.repository" }, entityManagerFactoryRef = "ATSEntityManagerFactory"
						, transactionManagerRef = "ATSTransactionManager")
public class DataSourceConfig {
	/**
	 * Create JpaTransactionManager for ats.
	 * 
	 * @return JpaTransactionManager for ats.
	 * @throws SQLException
	 *             SQLException
	 */
	@Bean(name = "ATSTransactionManager")
	public JpaTransactionManager ATSTransactionManager() throws SQLException {
		return new JpaTransactionManager(ATSEntityManagerFactory().getObject());
	}

	
	
	/**
	 * Create data source instance for ats.
	 * 
	 * @return data source instance for ats
	 * @throws SQLException
	 *             SQLException
	 */
	@Bean(name = "ATSEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean ATSEntityManagerFactory() throws SQLException {
		// TODO Auto-generated method stub
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(false);
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("atsPersistenceUnit");
		factoryBean.setDataSource(atsDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan("com.ats.*");
		return factoryBean;
	}

	/**
	 * Create data source instance for ats
	 * 
	 * @return data source instance for ats
	 * @throws SQLException
	 *             SQLException
	 * 
	 */
	@Primary
	@Bean(name = "atsDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	DataSource atsDataSource() {
		return DataSourceBuilder.create().build();
	}

}

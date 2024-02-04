package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DataSourceConfiguration {

	@Primary
	@Bean(name = "firstDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.first")
	public DataSource firstDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.second")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public ChainedTransactionManager transactionManager(
			@Qualifier("firstDataSource") DataSource firstDataSource,
			@Qualifier("secondDataSource") DataSource secondDataSource) {
		return new ChainedTransactionManager(
				new DataSourceTransactionManager(firstDataSource),
				new DataSourceTransactionManager(secondDataSource));
	}
}

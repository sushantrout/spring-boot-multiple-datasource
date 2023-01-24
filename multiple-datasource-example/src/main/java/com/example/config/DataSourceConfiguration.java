package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Bean(name = "datasource1")
	@ConfigurationProperties("database1.datasource")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "datasource2")
	@ConfigurationProperties("database2.datasource")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "tm1")
	@Autowired
	@Primary
	DataSourceTransactionManager tm1(@Qualifier("datasource1") DataSource datasource) {
		DataSourceTransactionManager txm = new DataSourceTransactionManager(datasource);
		return txm;
	}

	@Bean(name = "tm2")
	@Autowired
	DataSourceTransactionManager tm2(@Qualifier("datasource2") DataSource datasource) {
		DataSourceTransactionManager txm = new DataSourceTransactionManager(datasource);
		return txm;
	}

	@Bean(name = "chainedTransactionManager")
	public ChainedTransactionManager getChainedTransactionManager(@Qualifier("tm1") DataSourceTransactionManager tm1,
			@Qualifier("tm2") DataSourceTransactionManager tm2) {
		return new ChainedTransactionManager(tm1, tm2);
	}
}

package com.douk.web.cfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Import({ServletConfig.class,MyBatisConfig.class})
@Configuration
@ComponentScan(basePackages= {"com.douk.web"})
public class RootConfig {
	 @Bean(name="dataSource")
	 public DataSource dataSource() {
			
			  DriverManagerDataSource dataSource = new DriverManagerDataSource();
			    dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
			    dataSource.setUrl("jdbc:mariadb://172.168.0.121/wegodb");
			    dataSource.setUsername("wego");
			    dataSource.setPassword("wego");
	
			    return dataSource;
	 }
	/* @Bean
	 public DataSourceTransactionManager txMannager() {
		 return new DataSourceTransactionManager(dataSource());
	 }*/
}
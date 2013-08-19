package com.mvc.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages={"com.mvc.controller","com.mvc.dao","com.mvc.service","com.mvc.validation"})
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {

	private static final String PROP_DB_DRIVER = "db.driver";
	private static final String PROP_DB_URL = "db.url";
	private static final String PROP_DB_USER_NAME = "db.user";
	private static final String PROP_DB_USER_PASSWORD = "db.password";
	private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialet";
	private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROP_ENTITY_MGR_PACKAGE_SCAN = "entitymanager.package.to.scan";
	private static final String PROP_MSG_SOURCE = "message.source.basename";
	
	
	@Resource
	Environment environment;
	
	@Bean
	public InternalResourceViewResolver  getInternalResouceViewResolver() {
				
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		System.out.println(viewResolver.toString());
		return viewResolver;
	}
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
	}
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public DataSource getDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty(PROP_DB_DRIVER));
		dataSource.setUrl(environment.getRequiredProperty(PROP_DB_URL));
		dataSource.setUsername(environment.getRequiredProperty(PROP_DB_USER_NAME));
		dataSource.setPassword(environment.getRequiredProperty(PROP_DB_USER_PASSWORD));
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDataSource());
		sessionFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROP_ENTITY_MGR_PACKAGE_SCAN));
		sessionFactoryBean.setHibernateProperties(getHibernatProperties());
		return sessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	
	
	
	private Properties getHibernatProperties() {
		Properties properties = new Properties();
		properties.put(PROP_HIBERNATE_DIALECT, environment.getRequiredProperty(PROP_HIBERNATE_DIALECT));
		properties.put(PROP_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
		return properties;
	}
	
	
	@Bean
	public ResourceBundleMessageSource getMessageResouce(){
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(environment.getRequiredProperty(PROP_MSG_SOURCE));
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
}

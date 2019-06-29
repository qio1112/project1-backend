package com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@ComponentScan(basePackages = "com.routes")
@EnableWebMvc //annotation everywhere
@EnableTransactionManagement
@PropertySource("classpath:/mysql.properties")
public class AppConfig implements WebMvcConfigurer{
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver vr = new  InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/");
		vr.setSuffix(".jsp");
		return vr;
	}
//	@Autowired
//	private Environment env; 
//	
//	private Logger logger = Logger.getLogger(this.getClass().getName());
//	
//	@Bean
//	public DataSource myDataSource() {
//		
//		// create connection pool
//		ComboPooledDataSource myDataSource = new ComboPooledDataSource();
//
//		// set the jdbc driver
//		try {
//			myDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");		
//		}
//		catch (PropertyVetoException exc) {
//			throw new RuntimeException(exc);
//		}
//		
//		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
//		
//		// set database connection props
//		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//		myDataSource.setUser(env.getProperty("jdbc.username"));
//		myDataSource.setPassword(env.getProperty("jdbc.password"));
//		
//		// set connection pool props
//		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
//		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
//		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
//		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
//
//		return myDataSource;
//	}
//	
//	private Properties getHibernateProperties() {
//
//		// set hibernate properties
//		Properties props = new Properties();
//
//		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		
//		return props;				
//	}
//	
//	@Bean
//	public LocalSessionFactoryBean sessionFactory(){
//		
//		// create session factorys
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		
//		// set the properties
//		sessionFactory.setDataSource(myDataSource());
//		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
//		sessionFactory.setHibernateProperties(getHibernateProperties());
//		
//		return sessionFactory;
//	}
//	
//	@Bean
//	@Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//		
//		// setup transaction manager based on session factory
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(sessionFactory);
//
//		return txManager;
//	}	
//	
////	@Bean
////	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
////		return new PropertySourcesPlaceholderConfigurer();
////	}
////	
//	private int getIntProperty(String propName) {
//		String propVal = env.getProperty(propName);
//		int intPropVal = Integer.parseInt(propVal);
//		
//		return intPropVal;
//	}	
}

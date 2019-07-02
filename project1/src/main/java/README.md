# Readme.md
## Hibernate Annotation Configuration
## Dependency Injection in pom.xml
* hibernate core
* spring-orm
* mysql connecter
* JDBC connection pooling

```
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>5.4.3.Final</version>
</dependency>

<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-orm</artifactId>
	    <version>5.1.5.RELEASE</version>
	</dependency>

<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.16</version>
</dependency>

<dependency>
	    <groupId>com.mchange</groupId>
	    <artifactId>c3p0</artifactId>
	    <version>0.9.5.2</version>
	</dependency>
```

## Configuration steps:
 ### Create a @Configuration class
* Instead of using {}-servlet.xml file to configure hibernate in our project, we use annotation to configure Hibernate.
* Create a @Configuration class AppConfig.java under src/main/java/com.config/
* In this class, we are going to set a logical group of beans for Hibernate Annotation. We need @Autowired the Environment env to read the property file for the beans.
* To make sure we are reading the data, we enable the debug logs by a set of Loggers and log a conditional report in the console.
### Set up Database Properties
* Declare a Bean for datasource by using @Bean,
Specify the bean name by @Bean(name = "dataSource")
* In the datasource bean, we create connection pool by using ComboPooledDataSource. We can set the connection pool properties by using its methods to set pool size and idle time.
The connection pool can reduces the number of times new connection objects are created and promotes connection object reuse.
* Set the database connection properties and connection pool properties. The properties can be imported by using the @PropertySource("classpath:/mysql.properties") at the AppConfig class.
* Set up database properties for database information, and connection pool properties.
stored at : /src/main/resources/mysql.properties
```
#JDBC  connection properties
#
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/database?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
jdbc.user=root
jdbc.username=root
jdbc.password=<password>
#
#Connection pool properties
#
connection.pool.initialPoolSize=5
connection.pool.minPoolSize=5
connection.pool.maxPoolSize=20
connection.pool.maxIdleTime=3000
```
* The benefits for using seperated beans and property file are easy to change Database method. For example, if we want to change MySQL to SqlServer, we only need to change the information at the property file.
### Set up Hibernate Properties.
* We create a property class by implementing Properties getHibernateProperties(). We also write the Hibernate properties in the mysql.properties. The properties can be imported by using the @PropertySource("classpath:/mysql.properties") at the AppConfig class.
```
# Hibernate properties
#
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.show_sql=true
hibernate.packagesToScan=com.entity
```
### Create SessionFactory
* Declare another @Bean by implementing LocalSessionFactoryBean sessionFactory(), so we can use session methods and enable transaction manager to process our data queries.
* The session factory properties will be defined in the bean.
### Enable Transaction Manager
* Declare @Bean and @Autowired by implementing HibernateTransactionManager transactionManager() to keep ACID principles, which means we can use @Transactinal while we are calling services.
### Declare private functions used inside the AppConfig.java class
* Declare a private int  getIntProperty( ) function to parse String to int
### Check all the annotations for the AppConfig.java class
```
@Configuration
@ComponentScan(basePackages = "com.*")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:/mysql.properties")
public class AppConfig {
                    @Autowired
                    private Environment env
}
```

* We use the @ComponentScan(basePackages = "com.*) along with @Configuration to specify the packages that we want to be scanned in our project.
* @EnableWebMvc is to enable Spring MVC support through a Java configuration class, such as such as registering controllers and mappings, type converters, validation support, message converters and exception handling.
* @EnableTransactionManagement annotation that we can use in a @Configuration class and enable transactional support.

###  The annotation configuration of Hibernate in this Spring MVC project is done.

package com.niit.collab.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niit.collab.dao.BlogDAO;
import com.niit.collab.dao.BlogDAOImpl;
import com.niit.collab.dao.EventDAO;
import com.niit.collab.dao.EventDAOImpl;
import com.niit.collab.dao.ForumCommentDAO;
import com.niit.collab.dao.ForumCommentDAOImpl;
import com.niit.collab.dao.ForumDAO;
import com.niit.collab.dao.ForumDAOImpl;
import com.niit.collab.dao.FriendDAO;
import com.niit.collab.dao.FriendDAOImpl;
import com.niit.collab.dao.JobDAO;
import com.niit.collab.dao.JobDAOImpl;
import com.niit.collab.dao.UsersDAO;
import com.niit.collab.dao.UsersDAOImpl;
import com.niit.collab.model.Blog;
import com.niit.collab.model.Event;
import com.niit.collab.model.Forum;
import com.niit.collab.model.ForumComment;
import com.niit.collab.model.Friend;
import com.niit.collab.model.Job;
import com.niit.collab.model.Users;


@Configuration
@ComponentScan("com.niit.collab")
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationContextConfiguration {

	
	@Bean(name="dataSource")
	 public DataSource getDataSource(){
	DriverManagerDataSource dataSource=new DriverManagerDataSource();	
	dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	dataSource.setUsername("rishabh");
	dataSource.setPassword("rish");
	return dataSource;
	}

private Properties getHibernateProperties(){
	Properties properties=new Properties();
	properties.put("hibernate.show_sql", "true");
	properties.put("hibernate.dialect","org.hibernate.dialect.OracleDialect");
	properties.put("hibernate.hbm2ddl.auto", "update");
	return properties;
}

@Autowired
@Bean(name="sessionFactory")
public SessionFactory getSessionFactory(DataSource dataSource){
	LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
	sessionBuilder.addProperties(getHibernateProperties());
	sessionBuilder.addAnnotatedClass(Blog.class);
	sessionBuilder.addAnnotatedClass(Users.class);
	sessionBuilder.addAnnotatedClass(Event.class);
	sessionBuilder.addAnnotatedClass(Forum.class);
	sessionBuilder.addAnnotatedClass(Job.class);
	sessionBuilder.addAnnotatedClass(Friend.class);
	sessionBuilder.addAnnotatedClass(ForumComment.class);
	return sessionBuilder.buildSessionFactory();
	
}

@Autowired
@Bean(name="transactionManager")
public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
HibernateTransactionManager transactionManager=new 	HibernateTransactionManager(sessionFactory);
return transactionManager;
	
}
@Autowired
@Bean(name="blogDAO")
public BlogDAO getBlogDAO(SessionFactory sessionFactory){
	
	return new BlogDAOImpl(sessionFactory);
}

@Autowired
@Bean(name="usersDAO")
public UsersDAO getUsersDAO(SessionFactory sessionFactory){
	
	return new UsersDAOImpl(sessionFactory);
}
@Autowired
@Bean(name="eventDAO")
public EventDAO getEventDAO(SessionFactory sessionFactory){
	
	return new EventDAOImpl(sessionFactory);
}
@Autowired
@Bean(name="forumDAO")
public ForumDAO getForumDAO(SessionFactory sessionFactory){
	
	return new ForumDAOImpl(sessionFactory);
}
@Autowired
@Bean(name="jobDAO")
public JobDAO getJobDAO(SessionFactory sessionFactory){
	
	return new JobDAOImpl(sessionFactory);
}
@Autowired
@Bean(name="friendDAO")
public FriendDAO getFriendDAO(SessionFactory sessionFactory){
	
	return new FriendDAOImpl(sessionFactory);
}
@Autowired
@Bean(name="forumCommentDAO")
public ForumCommentDAO getForumCommentDAO(SessionFactory sessionFactory){
	return new ForumCommentDAOImpl(sessionFactory);
}
}

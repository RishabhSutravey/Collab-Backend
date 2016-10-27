package com.niit.collab.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collab.dao.UsersDAO;
import com.niit.collab.model.Users;


public class UsersTest {
	public static void main(String[] args) {

		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		System.out.println("scanning");
		context.scan("com.niit.collab");
		System.out.println("hello");
		context.refresh();
		

		
		UsersDAO usersDAO = (UsersDAO) context.getBean("usersDAO");		
		
		Users users = (Users) context.getBean("users");

		users.setId(4);
		/*users.setRole("LAPP");
		users.setAddress("hyderabad");
		users.setDob(new Date());
		users.setGender("male");
		users.setMail("rishsh@agmai");
		users.setMobile(7868575);
		users.setUsername("rishabh");
		users.setPassword("dkhdagf");*/
usersDAO.delete(users);
		/*if (usersDAO.saveOrUpdate(users) == true) {
			System.out.println("user created successfully");
		} else {
			System.out.println("not able to create category");
		}
*/
	}

}

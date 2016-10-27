package com.niit.collab.test;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.collab.dao.BlogDAO;
import com.niit.collab.model.Blog;


public class BlogTest {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		System.out.println("scanning");
		context.scan("com.niit.collab");
		System.out.println("hello");
		context.refresh();
		

		
		BlogDAO blogDAO = (BlogDAO) context.getBean("blogDAO");		
		
		Blog blog = (Blog) context.getBean("blog");

		//blog.setId(3);
		blog.setTitle("APPle");
		blog.setContent("this is mobile category");
		blog.setUserid(12);
		blog.setDoc(new Date());
//blogDAO.delete(blog);
		if (blogDAO.saveOrUpdate(blog) == true) {
			System.out.println("category created successfully");
		} else {
			System.out.println("not able to create category");
		}

	}
}

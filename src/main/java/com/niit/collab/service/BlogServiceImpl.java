package com.niit.collab.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.collab.dao.BlogDAO;
import com.niit.collab.model.Blog;

@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	private BlogDAO blogDAO;
	
	/*Create New Blog Service*/
	public void createBlog(Blog blog,HttpSession session) 
	{	
		/*Saving userid from session and time to blog object*/
		//blog.setB_userid((int)session.getAttribute("userid"));
		//blog.setBlogtime(new java.util.Date().toString());
		
		/*Saving blog in database*/
		blogDAO.saveOrUpdate(blog);	
	}

	public String getAllBlogs() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteBlog(int blogid) {
		// TODO Auto-generated method stub
		
	}

	public Blog editBlog(int blogid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Blog> getSingleBlog(int blogid) {
		// TODO Auto-generated method stub
		return null;
	}

	/*Retrive All Blog List and convert to JSON and saving in String */
	/*public String  getAllBlogs() 
	{
		List<Blog> list=blogDAO.getBlogs();
		Gson gson=new Gson();
		String data=gson.toJson(list);
		return data;
	}*/

	/*Delete single blog based on blog id
	public void deleteBlog(int blogid) 
	{
		//Blog blog= blogDAO.getBlog(blogid);
		blogDAO.deleteBlog(blog);
	}
*/
	/*Edit individual blog based on blog id
	public Blog editBlog(int blogid)
	{
		 return blogDAO.getBlog(blogid);
	}
	
	Retrive Single Blog data and convert to JSON and saving in String 
	public List<Blog> getSingleBlog(int blogid) 
	{
		return blogDAO.getIndividualForum(blogid);
	}*/
}

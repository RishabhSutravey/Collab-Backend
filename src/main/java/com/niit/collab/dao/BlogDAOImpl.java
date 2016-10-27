package com.niit.collab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.model.Blog;

@Repository(value="blogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
@Transactional
	public boolean saveOrUpdate(Blog blog) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			System.out.println("save");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<Blog> list() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Blog.class);
		List<Blog> list=c.list();
		return list;
	}
	@Transactional
	public Blog get(int id) {
		String hql = "from Blog where id= "+ "'"+ id+"'" ;
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog>list= query.list();
		
		if(list==null)
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}

}

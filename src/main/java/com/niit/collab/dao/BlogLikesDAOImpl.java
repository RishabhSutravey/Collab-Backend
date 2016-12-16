package com.niit.collab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.model.BlogLikes;

@Repository("blogLikesDAO")
public class BlogLikesDAOImpl implements BlogLikesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public BlogLikesDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean saveOrUpdate(BlogLikes blogLikes) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blogLikes);
			System.out.println("save");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(BlogLikes blogLikes) {
		try {
			sessionFactory.getCurrentSession().delete(blogLikes);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public BlogLikes list(int uid, int bid) {
		String hql="from Bloglikes where blogid='"+bid+"' and userid='"+uid+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
List<BlogLikes>list= query.list();
		
		if(list==null)
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
		
	}

	@Transactional
	public List<BlogLikes> bloglist(int bid) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(BlogLikes.class);
		c.add(Restrictions.eq("blogid",bid));
		List<BlogLikes> list=c.list();
        return list;
	}

}

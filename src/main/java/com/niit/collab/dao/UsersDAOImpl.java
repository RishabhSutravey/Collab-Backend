package com.niit.collab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.model.Users;

@Repository(value="usersDAO")
public class UsersDAOImpl implements UsersDAO {


	@Autowired
	private SessionFactory sessionFactory;
	public UsersDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean saveOrUpdate(Users users) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(users);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Users users) {
		try {
			sessionFactory.getCurrentSession().delete(users);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<Users> list() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Users.class);
		List<Users> list=c.list();
		return list;
	}

}

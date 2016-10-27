package com.niit.collab.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.model.Job;

@Repository(value="jobDAO")
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean saveOrUpdate(Job job) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(job);
			System.out.println("save");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

package com.niit.collab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.model.Friend;

@SuppressWarnings("deprecation")
@Repository(value="FriendDAO")
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public FriendDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
   @Transactional
	public boolean saveOrUpdate(Friend friend) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

   @Transactional
	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

   @Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Friend getFriend(int id) {
		String hql = "from Friend where id= "+ "'"+ id+"'" ;
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Friend>list= query.list();
		
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
	@SuppressWarnings("unchecked")
	public List<Friend> list() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Friend.class);
		List<Friend> list = c.list();
		return list;
	}

@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public Friend newrequest(int id) {
	String hql="from Friend where fid = "+"'"+id+"'"+"and status= 'n'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	List<Friend> list=query.list();
	if(list==null){
		return null;
	}else{
		return list.get(0);
	}
}

@Transactional
public List<Friend> getfriendlist(int uid) {
	String hql="from Friend where userid= "+uid;
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
	List<Friend> list = query.list();
	return list;
}

}

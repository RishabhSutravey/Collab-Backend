package com.niit.collab.dao;

import java.util.List;

import com.niit.collab.model.Users;

public interface UsersDAO {

	public boolean saveOrUpdate(Users users);
	public boolean delete(Users users);
	public List<Users>list();
}

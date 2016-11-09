package com.niit.collab.dao;

import java.util.List;

import com.niit.collab.model.Forum;

public interface ForumDAO {
	public boolean saveOrUpdate(Forum forum);
	public boolean delete(Forum forum);
public List<Forum>list();
public Forum getforum(int id);
}

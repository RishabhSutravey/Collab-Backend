package com.niit.collab.dao;

import com.niit.collab.model.Forum;

public interface ForumDAO {
	public boolean saveOrUpdate(Forum forum);
	public boolean delete(Forum forum);

}

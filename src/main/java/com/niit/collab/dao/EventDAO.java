package com.niit.collab.dao;

import com.niit.collab.model.Event;

public interface EventDAO {

	public boolean saveOrUpdate(Event event);
	public boolean delete(Event event);
}

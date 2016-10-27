package com.niit.collab.dao;

import com.niit.collab.model.Job;

public interface JobDAO {
	public boolean saveOrUpdate(Job job);
	public boolean delete(Job job);

}

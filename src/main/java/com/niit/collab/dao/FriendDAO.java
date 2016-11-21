package com.niit.collab.dao;

import java.util.List;

import com.niit.collab.model.Friend;

public interface FriendDAO {

	public boolean saveOrUpdate(Friend friend);
	public boolean delete(Friend friend);
	public Friend getFriend(int id);
	public Friend newrequest(int id);
	public List<Friend> getfriendlist();
	public List<Friend> list();
}

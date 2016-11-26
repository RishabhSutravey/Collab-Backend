package com.niit.collab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collab.dao.FriendDAO;
import com.niit.collab.dao.UsersDAO;
import com.niit.collab.model.Friend;
import com.niit.collab.model.Users;

@RestController
public class FriendController {
@Autowired
private FriendDAO friendDAO;
@Autowired
private UsersDAO usersDAO;

@PostMapping(value="/sendrequest/{fid}")
public ResponseEntity<Friend> newfriend(Friend friend,@PathVariable("fid") int fid,HttpSession session){
	friend.setFriendid(fid);
	int uid = (Integer) session.getAttribute("uid");
	friend.setUserid(uid);
	friend.setStatus('n');
	friendDAO.saveOrUpdate(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}

@GetMapping(value="/myfriends")
public ResponseEntity<List<Users>> myfriends(HttpSession session){
	int uid =(Integer) session.getAttribute("uid");
	List<Users> u1 = null;
	List<Friend> list=friendDAO.getfriendlist(uid);
	System.out.println(list.size());
	for(int i=0;i < list.size();i++){
		System.out.println(i);
		Friend friends = list.get(i);
		System.out.println("friend");
		 int f = friends.getFriendid();
		u1= usersDAO.getuser(f);
	    
		System.out.println(u1);
	}
	return new ResponseEntity<List<Users>>(u1,HttpStatus.OK);

}
@PostMapping(value="/acceptrequest")
public ResponseEntity<Friend> acceptfriend(HttpSession session){
	int fid = (Integer) session.getAttribute("uid");
	Friend friend =friendDAO.newrequest(fid);
	friend.setStatus('a');
	friendDAO.saveOrUpdate(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}
@PostMapping(value="/rejectrequest")
public ResponseEntity<Friend> rejectfriend(HttpSession session){
	int fid = (Integer) session.getAttribute("uid");
	Friend friend =friendDAO.newrequest(fid);
	friend.setStatus('r');
	friendDAO.saveOrUpdate(friend);
	return new ResponseEntity<Friend>(HttpStatus.OK);
}
}

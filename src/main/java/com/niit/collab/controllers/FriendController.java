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
public ResponseEntity<Friend> newfriend(Friend friend,@PathVariable("fid") String fid,HttpSession session){
	String uid=(String) session.getAttribute("username");
	friend.setUserid(uid);
	friend.setFriendid(fid);
	friend.setStatus('n');
	friend.setIsonline('o');
	friendDAO.saveOrUpdate(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}

@GetMapping(value="/myfriends")
public ResponseEntity<List<Friend>> myfriends(HttpSession session){
	String uid =(String) session.getAttribute("username");
	//List<Users> u1 = null;
	List<Friend> list=friendDAO.getfriendlist(uid);
	return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
}
@GetMapping(value="/newrequests")
public ResponseEntity<List<Friend>> newrequests(HttpSession session){
	String uid=(String) session.getAttribute("username");
	List<Friend> list=friendDAO.getrequestlist(uid);
	return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
}
@PostMapping(value="/acceptrequest/{fid}")
public ResponseEntity<Friend> acceptfriend(@PathVariable("fid") String fid,HttpSession session){
	String uid=(String) session.getAttribute("username");
	Friend friend=friendDAO.newrequest(fid, uid);
	friend.setStatus('a');
	friendDAO.saveOrUpdate(friend);
	Friend friend1=new Friend();
	friend1.setUserid(uid);
	friend1.setFriendid(fid);
	friend1.setStatus('a');
	friendDAO.saveOrUpdate(friend1);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}
@PostMapping(value="/rejectrequest/{fid}")
public ResponseEntity<Friend> rejectfriend(@PathVariable("fid") String fid,HttpSession session){
	String uid=(String) session.getAttribute("username");
	Friend friend=friendDAO.newrequest(fid, uid);
	friend.setStatus('r');
	friendDAO.saveOrUpdate(friend);
	return new ResponseEntity<Friend>(HttpStatus.OK);
}
@PostMapping("/unfriend/{fid}")
public ResponseEntity<Friend> unfriend(@PathVariable("fid") String fid,HttpSession session){
	return null;
	
}
}

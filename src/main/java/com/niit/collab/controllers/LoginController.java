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
public class LoginController {
	@Autowired 
	UsersDAO usersDAO;
	@Autowired
	FriendDAO friendDAO;

	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<List<Users>> login( @PathVariable("username") String username,@PathVariable("password") String password ,HttpSession session){
		Users users = usersDAO.authuser(username,password);
		if(users==null)
			{	return null;
	}else if(friendDAO.getfriendlist(username)==null){
		session.setAttribute("userLogged", users);
		session.setAttribute("uid", users.getId());
		session.setAttribute("username",users.getUsername());
		users.setStatus('o');
		usersDAO.saveOrUpdate(users);
		List<Users> users1=usersDAO.getuser(users.getId());
		return new ResponseEntity<List<Users>>(users1,HttpStatus.OK);
	}else{
		session.setAttribute("userLogged", users);
		session.setAttribute("uid", users.getId());
		session.setAttribute("username",users.getUsername());
		users.setStatus('o');
		usersDAO.saveOrUpdate(users);
    	List<Friend> friend=friendDAO.setonline(users.getUsername());
    	for(int i=0;i<friend.size();i++){
    		Friend online=friend.get(i);
    		online.setIsonline('o');
    		friendDAO.saveOrUpdate(online);
    	}
		List<Users> users1=usersDAO.getuser(users.getId());
		return new ResponseEntity<List<Users>>(users1,HttpStatus.OK);
	}
	}
	@PostMapping("/logout")
	public ResponseEntity<Users> logout(HttpSession session){
		int uid =  (Integer) session.getAttribute("uid");
		Users users =usersDAO.oneuser(uid);
		users.setStatus('f');
		usersDAO.saveOrUpdate(users);
		List<Friend> friend=friendDAO.setonline(users.getUsername());
		for(int i=0;i<friend.size();i++){
    		Friend online=friend.get(i);
    		online.setIsonline('f');
    		friendDAO.saveOrUpdate(online);
    	}
		session.invalidate();
		return new ResponseEntity<Users>(users,HttpStatus.OK);
	}
}

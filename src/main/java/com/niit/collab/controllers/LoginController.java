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

import com.niit.collab.dao.UsersDAO;
import com.niit.collab.model.Users;

@RestController
public class LoginController {
	@Autowired 
	UsersDAO usersDAO;

	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<Users> login( @PathVariable("username") String username,@PathVariable("password") String password ,HttpSession session){
		Users users = usersDAO.authuser(username,password);
		if(users==null)
			{	return null;
	}else{
		session.setAttribute("userLogged", users);
		session.setAttribute("uid", users.getId());
		users.setStatus('o');
		usersDAO.saveOrUpdate(users);
		return new ResponseEntity<Users>(users,HttpStatus.OK);
	}
	}
	@PostMapping("/logout")
	public ResponseEntity<Users> logout(HttpSession session){
		int uid =  (Integer) session.getAttribute("uid");
		Users users =usersDAO.logout(uid);
		users.setStatus('f');
		usersDAO.saveOrUpdate(users);
		session.invalidate();
		return new ResponseEntity<Users>(users,HttpStatus.OK);
	}
}

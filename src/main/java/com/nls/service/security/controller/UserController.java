package com.nls.service.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nls.service.security.dao.UserDao;
import com.nls.service.security.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserDao userdao;
	
	@GetMapping
	public List<User> getUsers() {
		return userdao.getAllUser();
	}
	@PostMapping
	public User saveUser(@RequestBody User newUser) {
		userdao.addNew(newUser);
		return newUser;
	}
	
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		
		return userdao.findUserById(id);
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		User updatedUser = userdao.updateUser(user);
		return updatedUser;	
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userdao.deleteUserById(id);
		return "user id : "+id+" DELETED ";
	}
	

}

package com.rgt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.entity.UserEntity;
import com.rgt.service.UserService;
import com.rgt.util.RoleAuthenticate;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	UserEntity createAccount(@RequestBody UserEntity userEntity) throws IOException {
		return userService.createAccount(userEntity);
	}
	@GetMapping
	@RoleAuthenticate("admin")
	List<UserEntity> getAllUsers() throws IOException, ClassNotFoundException{
		return userService.getAllUsers();
	}
	
	@GetMapping("{userId}")
	@RoleAuthenticate("admin")
	UserEntity getUser(@PathVariable("userId") Long userId) throws IOException, ClassNotFoundException {
		return userService.getUser(userId);
	}
	
	@PutMapping("/{userId}")
	UserEntity updateUser(@RequestBody UserEntity userEntity,@PathVariable("userId") Long userId) throws ClassNotFoundException, IOException {
		return userService.updateUser(userEntity,userId);
	}
	
	@DeleteMapping("/{userId}")
	String deleteUser(@PathVariable("userId") Long userId) {
		return userService.deleteUser(userId)? "Success":"Failed To delete";
	}

	// Exception handling logic
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> handleMyException(IllegalAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Access Denied.");
    }	
}

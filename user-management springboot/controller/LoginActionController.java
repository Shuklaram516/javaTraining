package com.rgt.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.service.UserService;

@RestController
@RequestMapping("/auth")
public class LoginActionController {
	@Autowired
	private UserService userService;

	@PostMapping("/login/{userName}/{password}")
	public String login(@PathVariable Long userName, @PathVariable String password)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		if (userService.authenticateCredentials(userName, password)) {
			return "Successfully logged in";
		}
		return "failed to login";

	}

	@GetMapping("/logout")
	public String logout() {
		userService.logout();
		return "Successfully logged out";
	}

}

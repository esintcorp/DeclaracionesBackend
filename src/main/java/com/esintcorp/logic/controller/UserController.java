package com.esintcorp.logic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/register")
    public User createUser(@Valid @RequestBody User user) {
		System.out.println("USER: " + user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

package com.esintcorp.logic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
    public User createQuestion(@Valid @RequestBody User user) {
		System.out.println("USER: " + user);
        return userRepository.save(user);
    }
}

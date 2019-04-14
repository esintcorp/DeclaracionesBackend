package com.esintcorp.logic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.SubscriptionPeriod;
import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.SubscriptionPeriodRepository;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubscriptionPeriodRepository subscriptionPeriodRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/register")
    public List<SubscriptionPeriod> createUser(@Valid @RequestBody User user) {
		System.out.println("USER: " + user);
		User userFound;
		if (user.getRucNumber() == null || user.getRucNumber().isEmpty()) {
			user.setRucNumber("111");
			userFound = userRepository.findByIdCard(user.getIdCard());
			if (userFound != null) {
				throw new IllegalArgumentException("Esta Cédula ya existe!");
			}
			
			userFound = userRepository.findByEmail(user.getEmail());
			if (userFound != null) {
				throw new IllegalArgumentException("Este Email ya existe!");
			}
		} else {
			userFound = userRepository.findByRucNumber(user.getRucNumber());
			if (userFound != null) {
				throw new IllegalArgumentException("Este RUC ya existe!");
			}
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return subscriptionPeriodRepository.findActive();
    }

	@PostMapping("/getUser")
    public List<SubscriptionPeriod> getUser(HttpServletRequest request) {
		System.out.println("id: " + request.getSession().getAttribute("UserID"));
		return null;
	}
}

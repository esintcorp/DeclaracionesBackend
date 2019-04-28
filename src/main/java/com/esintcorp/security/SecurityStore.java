package com.esintcorp.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class SecurityStore {
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/getSessionInfo")
	public User getSessionInformation() {
		System.out.println("GET S I");
		User user = userRepository.findById((Long) httpSession.getAttribute("UserID")).get();
		return user;
	}
}

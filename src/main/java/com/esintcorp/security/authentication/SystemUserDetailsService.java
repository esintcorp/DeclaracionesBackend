package com.esintcorp.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.UserRepository;

@Service
public class SystemUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(" USERNAME::: " + username);
		User user = userRepository.findByEmail(username);
//		return new AuthenticableUser(username, user.getPassword(), true, true, true, true, null);
		return new MyUserPrincipal(user);
	}

}

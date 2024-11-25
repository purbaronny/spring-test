package com.test.conttoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Users;
import com.test.service.UsersService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/test/users")
public class UsersController {

	
	@Autowired
	private UsersService usersService;
	
	
	@PostMapping
	public Users save(@RequestBody Users users) {
		return usersService.save(users);
	}
	
	@PostMapping("/login")
	public Users login(@RequestBody RequestLogin requestLogin) throws RuntimeException {
		Users users = usersService.login(requestLogin);
		
		if(users == null) {
			throw new RuntimeException("Users is not found");
		}
		
		String password = "";
		if(null != users.getPassword()) {
			password = users.getPassword();
		}
		
		
		
		 //InMemoryUserDetailsManager inMemoryUserDetailsManager =  new InMemoryUserDetailsManager(user);
		return users;
	}
}

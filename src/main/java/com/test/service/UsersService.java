package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.conttoller.RequestLogin;
import com.test.entity.Users;
import com.test.entity.repo.UsersRepo;

@Service
@Transactional
public class UsersService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersRepo usersRepo;
	
	public Users save(Users users) {
		return usersRepo.save(users);
	}
	
	public Users login(RequestLogin requestLogin) {
		Iterable<Users> usersList = usersRepo.findByEmailContains(requestLogin.getEmail());
		
		Users users = null;
		if(usersList.iterator().hasNext()) {
			users = usersList.iterator().next();
		}
		
		if(users == null) {
			return null;
		}
		
		if(("Google").equals(requestLogin.getLoginType())) {
			return users;
		}
		
		if(users.getPassword().equals(requestLogin.getPassword())) {
			return users;
		}
		
		return users;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		Iterable<Users> usersList = usersRepo.findByEmailContains(username);
//		
//		Users users = null;
//		if(usersList.iterator().hasNext()) {
//			users = usersList.iterator().next();
//		}
//		
//		if(users == null) {
//			return null;
//		}
//		
//		UserDetails userDetails = User.builder()
//                .username(users.getEmail())  // Nama pengguna (email)
//                .password(passwordEncoder.encode(users.getPassword()))
//                
//                .roles("USER")  // Menambahkan peran (role) pengguna
//                .build();
//		
//		return userDetails;
//	}
}

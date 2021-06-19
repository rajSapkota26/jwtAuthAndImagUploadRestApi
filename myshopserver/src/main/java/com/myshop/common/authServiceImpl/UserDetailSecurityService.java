package com.myshop.common.authServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myshop.common.authService.UserService;
import com.myshop.common.authmodel.User;
@Service
public class UserDetailSecurityService implements UserDetailsService{
	@Autowired
	   private UserService service;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	       User user= service.getByuserName(username);
	       if (user==null){
	           throw new  UsernameNotFoundException("User not found");
	       }
	        return user;
	}

}

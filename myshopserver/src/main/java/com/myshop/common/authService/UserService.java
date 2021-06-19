package com.myshop.common.authService;

import java.util.*;

import com.myshop.common.authmodel.User;
import com.myshop.common.authmodel.UserRole;

public interface UserService {
	public User createUser(User user, Set<UserRole> roles) throws Exception;

	public User getByuserName(String userName);

	public void deleteUser(Long id);

	public User updateUser(User user);

	public List<User> getAllUser();
	    }

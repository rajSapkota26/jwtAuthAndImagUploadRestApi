package com.myshop.common.authServiceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.common.authRepository.RoleRepository;
import com.myshop.common.authRepository.UserProfileRepository;
import com.myshop.common.authRepository.UserRepository;
import com.myshop.common.authService.UserService;
import com.myshop.common.authmodel.User;
import com.myshop.common.authmodel.UserRole;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserProfileRepository profileRepo;

	@Override
	public User createUser(User user, Set<UserRole> roles) throws Exception {
		User existUser = this.userRepo.findByuserName(user.getUserName());
		if (existUser != null) {
			throw new Exception("User Already exist!!");
		} else {
			for (UserRole ur : roles) {
				roleRepo.save(ur.getRole());
			}
			user.getUserRole().addAll(roles);
			
			existUser=this.userRepo.save(user);
		}
		return existUser;
	}

	@Override
	public User getByuserName(String userName) {
		return this.userRepo.findByuserName(userName);
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepo.deleteById(id);

	}

	@Override
	public User updateUser(User user) {
		return this.userRepo.save(user);

	}

	@Override
	public List<User> getAllUser() {
		return this.userRepo.findAll();
	}

}

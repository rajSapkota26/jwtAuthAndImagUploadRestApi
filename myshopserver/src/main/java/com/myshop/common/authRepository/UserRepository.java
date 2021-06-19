package com.myshop.common.authRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshop.common.authmodel.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByuserName(String userName);

}

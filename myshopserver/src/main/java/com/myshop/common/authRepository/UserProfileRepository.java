package com.myshop.common.authRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshop.common.authmodel.UserProfile;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

}

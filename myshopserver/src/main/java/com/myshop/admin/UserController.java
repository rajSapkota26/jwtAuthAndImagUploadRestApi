package com.myshop.admin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.common.authService.UserService;
import com.myshop.common.authmodel.Role;
import com.myshop.common.authmodel.User;
import com.myshop.common.authmodel.UserRole;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	 @Autowired
	    private UserService userService;

	    @Autowired
	    private BCryptPasswordEncoder encoder;
	  //create user
	    @PostMapping("/")
	    public User createUser(@RequestBody User user) throws Exception {
	        Set<UserRole> roles=new HashSet<>();
	        UserRole userRole=  new UserRole();
	        userRole.setRole(new Role(2L,"USER"));
	        userRole.setUser(user);
	        roles.add(userRole);
	        user.setPassword(encoder.encode(user.getPassword()));
	        User user1 = this.userService.createUser(user, roles);
	        return user1;
	    }
	    //update user
	    @PutMapping("/")
	    public User updateUser(@RequestBody User user) throws Exception {	    	
	    	return this.userService.updateUser(user);
	    }
	    //get user by userName
	    @GetMapping("/{userName}")
	    public User getUser(@PathVariable("userName") String userName) throws Exception {
	        User user=this.userService.getByuserName(userName);
	        return user;
	    }
	    //delete user
	    @DeleteMapping("/{userId}")
	    public  void deleteUser(@PathVariable("userId") Long userId){
	        userService.deleteUser(userId);
	    }

	    //handle exception
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleException(){
	    return     ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Some thing went wrong");
	    }
}

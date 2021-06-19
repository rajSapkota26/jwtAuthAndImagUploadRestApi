package com.myshop.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.common.authServiceImpl.UserDetailSecurityService;
import com.myshop.common.authmodel.JWTRequest;
import com.myshop.common.authmodel.JWTResponse;
import com.myshop.common.authmodel.User;
import com.myshop.security.JWTUtils;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailSecurityService userDetailSecurityService;

	@Autowired
	private JWTUtils jwtUtils;

	// generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest request) throws Exception {
		try {
			authenticate(request.getUsername(), request.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		UserDetails userDetails = this.userDetailSecurityService.loadUserByUsername(request.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);

		return ResponseEntity.ok(new JWTResponse(token));

	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// fetching the current login user
	@GetMapping("/currentUser")
	public User getCurrentUser(Principal principal) {
		User userDetails = (User) userDetailSecurityService.loadUserByUsername(principal.getName());
		return userDetails;
	}
}

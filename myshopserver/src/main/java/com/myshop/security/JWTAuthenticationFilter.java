package com.myshop.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.myshop.common.authServiceImpl.UserDetailSecurityService;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailSecurityService service;
	@Autowired
	private JWTUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		// get token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			try {
				jwtToken = requestTokenHeader.substring(7);
				username = jwtUtils.extractUsername(jwtToken);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("invalid token!!");
		}
		// validate token
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.service.loadUserByUsername(username);
			if (jwtUtils.validateToken(jwtToken, userDetails)) {
				// set Authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} else {
			System.out.println("Token is not valid");
		}
		filterChain.doFilter(request, response);

	}

}

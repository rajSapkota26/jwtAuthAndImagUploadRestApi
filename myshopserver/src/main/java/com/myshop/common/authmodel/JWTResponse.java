package com.myshop.common.authmodel;

public class JWTResponse {
	private String token;

	public JWTResponse(String token) {
		this.token = token;
	}

	public JWTResponse() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

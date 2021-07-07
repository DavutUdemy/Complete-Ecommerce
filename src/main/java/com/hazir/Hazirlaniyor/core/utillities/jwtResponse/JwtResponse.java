package com.hazir.Hazirlaniyor.core.utillities.jwtResponse;

import java.util.Date;

public class JwtResponse {
	private String token ;
	private Date   tokenExpirationDate;

	public JwtResponse() {
	}

	public JwtResponse(String token, Date tokenExpirationDate) {
		this.token = token;
		this.tokenExpirationDate = tokenExpirationDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpirationDate() {
		return tokenExpirationDate;
	}

	public void setTokenExpirationDate(Date tokenExpirationDate) {
		this.tokenExpirationDate = tokenExpirationDate;
	}
}

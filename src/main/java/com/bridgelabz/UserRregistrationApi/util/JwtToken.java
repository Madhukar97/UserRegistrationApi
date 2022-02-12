package com.bridgelabz.UserRregistrationApi.util;

import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtToken {
	
	private final String SECRET_KEY = "secret";
	
	public String createToken (String username, String password) {
		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
		 
		Map<String, Object> payloadClaims = new HashMap<>();
		payloadClaims.put("username", username);
		payloadClaims.put("password", password);
		
		String token = JWT.create()
			        .withPayload(payloadClaims)
			        .sign(algorithm);
		 
		return token;
		
	}

}

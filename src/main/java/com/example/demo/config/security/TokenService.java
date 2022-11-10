package com.example.demo.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${demo.jwt.expiration}")
	private Long tokenExpiration;

	@Value("${demo.jwt.secret}")
	private String tokenSecret;

	public String getToken(Authentication authentication) {

		UserAuth user = (UserAuth) authentication.getPrincipal();
		Date today = new Date();
		Date expiration = new Date();
		expiration.setTime(today.getTime() + tokenExpiration);

		return Jwts.builder().setIssuer("API de Teste Vander").setSubject(user.getId().toString())
				.setIssuedAt(new Date()).setExpiration(expiration).signWith(SignatureAlgorithm.HS256, tokenSecret)
				.compact();
	}

	public Boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token);		
			return true;
		}catch(Exception e){
			return false;	
		}
		
	}

	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}

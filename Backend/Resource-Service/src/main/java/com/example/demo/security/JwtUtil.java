package com.example.demo.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String SECRET ="thisIsAVerySecureKeyBecauseItNeedsToBeAtLeast256BitsLongForHS256Algorithm";
	
	public String generateToken(String email, String role, Integer id) {
	
	return Jwts.builder()
			.setSubject(email)
			.claim("role", role)
			.claim("id", id)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
			.signWith(SignatureAlgorithm.HS256, SECRET)
			.compact();
	}
	public String extractEmail(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}

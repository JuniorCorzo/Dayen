package com.dayen.dayen.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
	private final JwtEncoder encoder;

	public TokenService(JwtEncoder encoder) {
		this.encoder = encoder;
	}

	public String generateToken(Authentication authentication){
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("Dayen")
				.issuedAt(now)
				.expiresAt(now.plus(90, ChronoUnit.DAYS))
				.subject(authentication.getName())
				.build();

		return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}

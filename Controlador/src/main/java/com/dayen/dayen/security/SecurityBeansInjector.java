package com.dayen.dayen.security;

import com.dayen.dayen.config.RsaKeyProperties;
import com.dayen.dayen.services.CustomUserDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeansInjector {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityBeansInjector.class);
	private final RsaKeyProperties rsaKeyProperties;
	private final CustomUserDetailsService customUserDetailsService;


	public SecurityBeansInjector(RsaKeyProperties rsaKeyProperties, CustomUserDetailsService customUserDetailsService) {
		this.rsaKeyProperties = rsaKeyProperties;
		this.customUserDetailsService = customUserDetailsService;
	}

	@Bean
	public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
		LOGGER.info("AuthenticationManager personalizado");

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(provider);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public JwtDecoder jwtDecoder(){
		return NimbusJwtDecoder
				.withPublicKey(rsaKeyProperties.publicKey())
				.build();
	}

	@Bean
	public JwtEncoder jwtEncoder(){
		JWK jwk = new RSAKey.Builder(rsaKeyProperties.publicKey())
				.privateKey(rsaKeyProperties.privateKey())
				.build();

		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

		return new NimbusJwtEncoder(jwks);
	}
}

package com.dayen.dayen.services;

import com.dayen.dayen.repository.UsuarioRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Service

public class RecuperacionClaveService {
	private final UsuarioRepository usuarioRepository;


	public RecuperacionClaveService(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}

	public void createTokenRecuperacion(@NotNull @Email String correo){
		this.usuarioRepository.updateTokenRecuperacion(correo, generateToken());
	}
	public void changeClave(String token, String newClave){
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		if (isExpire(token)){
			throw new RuntimeException("El token expiro");
		}

		if (!this.usuarioRepository.existsByToken(token)){
			throw new RuntimeException("El token no existe");
		}

		this.usuarioRepository.updateClaveRecuperacion(token, passwordEncoder.encode(newClave));
		this.usuarioRepository.resetToken(token);
	}

	private String generateToken(){
		String rawToken = UUID.randomUUID() + "."
				+ Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli();
		return encodeBase64UrlSafe(rawToken);
	}

	private Boolean isExpire(String token){
		if(token.isBlank())
			throw new RuntimeException("El token no puede ser nulo o no tener ningun caracter");

		String expireAt = decodeBase64UrlSafe(token).split("\\.")[1];
		return Instant.now().getEpochSecond() >= Long.parseLong(expireAt);
	}
	private String encodeBase64UrlSafe(String input){
		return Base64.getUrlEncoder().encodeToString(input.getBytes());
	}

	private String decodeBase64UrlSafe(String token){
		return new String(Base64.getUrlDecoder().decode(token));
	}
}

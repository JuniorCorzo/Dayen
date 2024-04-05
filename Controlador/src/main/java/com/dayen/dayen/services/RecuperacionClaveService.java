package com.dayen.dayen.services;

import com.dayen.dayen.repository.UsuarioRepository;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Service

public class RecuperacionClaveService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RecuperacionClaveService.class);
	private final UsuarioRepository usuarioRepository;
	private final EmailService emailService;
	private final ResourceLoader resourceLoader;
	private final PasswordEncoder passwordEncoder;
	@Value("${URL_RECUPERAR_CLAVE}")
	private String url;


	public RecuperacionClaveService(UsuarioRepository usuarioRepository, EmailService emailService, ResourceLoader resourceLoader) {
		this.usuarioRepository = usuarioRepository;
		this.emailService = emailService;
		this.resourceLoader = resourceLoader;

		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public void createTokenRecuperacion(@NotNull @Email String correo) {
		final String TOKEN = generateToken();
		this.usuarioRepository.updateTokenRecuperacion(correo, TOKEN);
		LOGGER.info("Se creo el token y se guardo correctamente");
		sendMail(correo, TOKEN);
	}

	public void changeClave(String token, String newClave)  {
		if (isExpire(token)) {
			this.usuarioRepository.resetToken(token);
			throw new RuntimeException("El token expiro");

		}

		if (!this.usuarioRepository.existsByToken(token)) {
			throw new RuntimeException("El token no existe");
		}

		this.usuarioRepository.updateClaveRecuperacion(token, passwordEncoder.encode(newClave));
		this.usuarioRepository.resetToken(token);
	}

	private String generateToken() {
		String rawToken = UUID.randomUUID() + "."
				+ Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli();
		return encodeBase64UrlSafe(rawToken);
	}

	private Boolean isExpire(String token) {
		if (token.isBlank())
			throw new RuntimeException("El token no puede ser nulo o no tener ningun caracter");

		String expireAt = decodeBase64UrlSafe(token).split("\\.")[1];
		return Instant.now().toEpochMilli() >= Long.parseLong(expireAt);
	}

	private String encodeBase64UrlSafe(String input) {
		return Base64.getUrlEncoder().encodeToString(input.getBytes());
	}

	private String decodeBase64UrlSafe(String token) {
		return new String(Base64.getUrlDecoder().decode(token));
	}

	private void sendMail(String correo, String TOKEN){
		String content;
		String subject = "Recuperación de contraseña - Dayen";
		try {
			Resource resource = resourceLoader
					.getResource("classpath:static/email_content.html");

			InputStream inputStream = resource.getInputStream();
			byte[] bData = FileCopyUtils.copyToByteArray(inputStream);

			content = new String(bData, StandardCharsets.UTF_8)
					.replace("{link}", url.concat("?token=").concat(TOKEN));

			this.emailService.sendMail(correo, subject, content);
		} catch (IOException | MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

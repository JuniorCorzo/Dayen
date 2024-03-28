package com.dayen.dayen.services;

import com.dayen.dayen.dao.request.LoginRequest;
import com.dayen.dayen.dao.response.LoginResponse;
import com.dayen.dayen.entity.Usuarios;
import com.dayen.dayen.exceptions.usuario.CredentialsNotValid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final TokenService tokenService;
	private final AuthenticationManager authManager;
	public AuthService(TokenService tokenService, AuthenticationManager authManager) {
		this.tokenService = tokenService;
		this.authManager = authManager;
	}

	public LoginResponse login(LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(loginRequest.idUsuario(),
						loginRequest.password());

		try {
			authManager.authenticate(authToken);
		} catch (AuthenticationException e){
			throw new CredentialsNotValid();
		}
		return new LoginResponse(this.tokenService.generateToken(authToken),
				loginRequest.idUsuario());
	}
}

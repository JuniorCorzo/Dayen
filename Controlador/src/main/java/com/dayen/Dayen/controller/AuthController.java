package com.dayen.Dayen.controller;

import com.dayen.Dayen.dao.request.LoginRequest;
import com.dayen.Dayen.dao.response.LoginResponse;
import com.dayen.Dayen.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		System.out.println("Login controlador");
		return ResponseEntity.ok(this.authService.login(loginRequest));
	}
}

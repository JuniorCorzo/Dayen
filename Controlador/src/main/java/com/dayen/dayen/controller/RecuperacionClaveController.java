package com.dayen.dayen.controller;

import com.dayen.dayen.services.RecuperacionClaveService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/v1/recuperar_clave")
public class RecuperacionClaveController {
	private final RecuperacionClaveService recuperacionClaveService;

	public RecuperacionClaveController(RecuperacionClaveService recuperacionClaveService) {
		this.recuperacionClaveService = recuperacionClaveService;
	}

	@PostMapping
	public void CreateToken(@RequestParam("correo") String correo){
		this.recuperacionClaveService.createTokenRecuperacion(correo);
	}

	@PutMapping("/{token}")
	public void ChangeClave(@PathVariable("token") String token, @RequestParam("clave") String newClave) throws MessagingException, UnsupportedEncodingException {
		this.recuperacionClaveService.changeClave(token, newClave);
	}
}

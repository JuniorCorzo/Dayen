package com.dayen.dayen.controller;

import com.dayen.dayen.dao.request.UsuarioRequest;
import com.dayen.dayen.entity.Usuarios;
import com.dayen.dayen.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/{idUsuario}")
	public Usuarios getUsuarioById(@PathVariable String idUsuario){
		return this.usuarioService.getUsuarioById(idUsuario);
	}

	@PostMapping("/create")
	public void createUsuario(@RequestBody UsuarioRequest usuario){
		this.usuarioService.usuarioCreate(usuario);
	}

	@PutMapping("/update")
	public void updateUsuario(@RequestBody UsuarioRequest usuario){
		this.usuarioService.usuarioUpdate(usuario);
	}
}

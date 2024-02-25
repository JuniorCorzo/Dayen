package com.dayen.Dayen.controller;

import com.dayen.Dayen.entity.Usuarios;
import com.dayen.Dayen.services.UsuarioService;
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
	public Usuarios createUsuario(@RequestBody Usuarios usuario){
		return this.usuarioService.usuarioCreate(usuario);
	}

	@PutMapping("/update")
	public Usuarios updateUsuario(@RequestBody Usuarios usuario){
		return this.usuarioService.usuarioUpdate(usuario);
	}
}

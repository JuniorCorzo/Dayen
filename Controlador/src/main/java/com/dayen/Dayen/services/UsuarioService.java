package com.dayen.Dayen.services;

import com.dayen.Dayen.dao.UsuarioRequest;
import com.dayen.Dayen.entity.Usuarios;
import com.dayen.Dayen.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuarios getUsuarioById(@NotNull String idUsuario){
		return this.usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario no existe"));
	}

	public void usuarioCreate(@Valid UsuarioRequest usuario) {
		if(usuarioRepository.existsById(usuario.idUsuario())){
			throw new RuntimeException("Usuario ya existe");
		}

		this.usuarioRepository.insertUsuario(usuario.idUsuario(), usuario.nombre(),
				usuario.apellido(), usuario.rol(), usuario.correo(), usuario.clave(),
				usuario.token());
	}

	public void usuarioUpdate(@Valid UsuarioRequest usuario) {
		if (!usuarioRepository.existsById(usuario.idUsuario()))
			throw new RuntimeException("Usuario no existe");

		this.usuarioRepository.updateUsuario(usuario.idUsuario(), usuario.nombre(),
				usuario.apellido(), usuario.rol(), usuario.correo(), usuario.clave(),
				usuario.token());
	}

}

package com.dayen.Dayen.services;

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

	public Usuarios usuarioCreate(@Valid Usuarios usuario) {
		if(!usuarioRepository.existsById(usuario.getIdUsuario())){
			throw new RuntimeException("Usuario ya existe");
		}
		return this.usuarioRepository.save(usuario);
	}

	public Usuarios usuarioUpdate(@Valid Usuarios usuario) {
		if (!usuarioRepository.existsById(usuario.getIdUsuario()))
			throw new RuntimeException("Usuario no existe");
		return this.usuarioRepository.save(usuario);
	}

}

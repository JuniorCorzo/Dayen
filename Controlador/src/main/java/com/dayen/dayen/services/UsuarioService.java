package com.dayen.dayen.services;

import com.dayen.dayen.dao.request.UsuarioRequest;
import com.dayen.dayen.entity.Usuarios;
import com.dayen.dayen.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public Usuarios getUsuarioById(@NotNull String idUsuario) {
		return this.usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario no existe"));
	}

	public void usuarioCreate(@Valid UsuarioRequest usuario) {
		if (usuarioRepository.existsById(usuario.idUsuario())) {
			throw new RuntimeException("Usuario ya existe");
		}

		this.usuarioRepository.insertUsuario(usuario.idUsuario(), usuario.nombre(),
				usuario.apellido(), usuario.rol(), usuario.correo(),
				passwordEncoder.encode(usuario.clave()), usuario.token());
	}

	public void usuarioUpdate(@Valid UsuarioRequest usuario) {
		if (!usuarioRepository.existsById(usuario.idUsuario()))
			throw new RuntimeException("Usuario no existe");

		this.usuarioRepository.updateUsuario(usuario.idUsuario(), usuario.nombre(),
				usuario.apellido(), usuario.rol(), usuario.correo(),
				passwordEncoder.encode(usuario.clave()), usuario.token());
	}

}

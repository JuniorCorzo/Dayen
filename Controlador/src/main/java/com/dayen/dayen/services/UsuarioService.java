package com.dayen.dayen.services;

import com.dayen.dayen.dao.request.UsuarioRequest;
import com.dayen.dayen.entity.Usuarios;
import com.dayen.dayen.exceptions.usuario.UserExists;
import com.dayen.dayen.exceptions.usuario.UserNotExists;
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
				.orElseThrow(UserNotExists::new);
	}

	public Usuarios usuarioCreate(@Valid UsuarioRequest usuario) {
		if (usuarioRepository.existsById(usuario.idUsuario())) {
			throw new UserExists();
		}

		return this.usuarioRepository.save(Usuarios.builder()
						.idUsuario(usuario.idUsuario())
						.username(usuario.nombre())
						.apellido(usuario.apellido())
						.rol(usuario.rol())
						.correo(usuario.correo())
						.clave(passwordEncoder.encode(usuario.clave()))
				.build());
	}

	public Usuarios usuarioUpdate(@Valid UsuarioRequest usuario) {
		if (!usuarioRepository.existsById(usuario.idUsuario()))
			throw new UserNotExists();

		return this.usuarioRepository.save(Usuarios.builder()
				.idUsuario(usuario.idUsuario())
				.username(usuario.nombre())
				.apellido(usuario.apellido())
				.rol(usuario.rol())
				.correo(usuario.correo())
				.clave(passwordEncoder.encode(usuario.clave()))
				.build());
	}

}

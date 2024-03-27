package com.dayen.dayen.services;

import com.dayen.dayen.entity.Usuarios;
import com.dayen.dayen.exceptions.usuario.UserNotExists;
import com.dayen.dayen.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
	private final UsuarioRepository usuarioRepository;

	public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String idUsuario) throws UsernameNotFoundException {
		LOGGER.info("Cargando usuario");
		Usuarios usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(UserNotExists::new);

		return org.springframework.security.core.userdetails.User
				.withUsername(usuario.getUsername())
				.password(usuario.getClave())
				.build();
	}
}

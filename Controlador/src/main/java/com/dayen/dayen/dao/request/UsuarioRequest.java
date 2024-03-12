package com.dayen.dayen.dao.request;

public record UsuarioRequest(
		String idUsuario,
		String nombre,
		String apellido,
		String rol,
		String correo,
		String clave,
		String token
) {}

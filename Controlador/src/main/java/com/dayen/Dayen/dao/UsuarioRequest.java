package com.dayen.Dayen.dao;

public record UsuarioRequest(
		String idUsuario,
		String nombre,
		String apellido,
		String rol,
		String correo,
		String clave,
		String token
) {
}

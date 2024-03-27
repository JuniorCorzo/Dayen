package com.dayen.dayen.dao.response;

import com.dayen.dayen.entity.Usuarios;

public record LoginResponse(String jwt, String idUsuario) {
}

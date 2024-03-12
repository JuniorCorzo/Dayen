package com.dayen.Dayen.dao.request;

import jakarta.persistence.Column;

public record LoteRequest(
		Integer idLote,
		String idUsuario,
		String fase,
		Integer hectareas
) {
}

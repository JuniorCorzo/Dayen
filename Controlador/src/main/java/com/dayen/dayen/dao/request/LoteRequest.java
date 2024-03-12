package com.dayen.dayen.dao.request;

public record LoteRequest(
		Integer idLote,
		String idUsuario,
		String fase,
		Integer hectareas
) {
}

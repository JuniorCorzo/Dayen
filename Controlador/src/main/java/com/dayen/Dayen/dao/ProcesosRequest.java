package com.dayen.Dayen.dao;

import java.time.LocalDateTime;

public record ProcesosRequest(
	Integer idLote,
	Integer idTipo,
	Integer idProducto,
	String descripcion,
	LocalDateTime realizadoEn
) {
}

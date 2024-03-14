package com.dayen.dayen.dao.request;

import java.time.LocalDateTime;

public record ProcesoRequest(
		Integer idProceso,
		Integer idLote,
		Integer idTipo,
		Integer idProducto,
		String descripcion,
		LocalDateTime realizadoEn
) {}
package com.dayen.dayen.dao.request;

import com.dayen.dayen.entity.Personal;

import java.time.LocalDate;
import java.util.List;

public record ProcesoRequest(
		Integer idProceso,
		Integer idLote,
		Integer idTipo,
		List<Integer> idProducto,
		String descripcion,
		LocalDate realizadoEn,
		List<Integer> idPersonal
) {}

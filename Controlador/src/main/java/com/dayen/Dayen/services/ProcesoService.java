package com.dayen.Dayen.services;

import com.dayen.Dayen.entity.Procesos;
import com.dayen.Dayen.repository.ProcesoRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoService {
	private final ProcesoRepository procesoRepository;

	public ProcesoService(ProcesoRepository procesoRepository) {
		this.procesoRepository = procesoRepository;
	}

	public List<Procesos> getAllProcesosByLote(@NotNull Integer idLote) {
		return this.procesoRepository.findAllByIdLote(idLote);
	}
}

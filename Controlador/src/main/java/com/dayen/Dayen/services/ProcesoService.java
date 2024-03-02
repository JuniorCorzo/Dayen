package com.dayen.Dayen.services;

import com.dayen.Dayen.dao.ProcesosRequest;
import com.dayen.Dayen.entity.Procesos;
import com.dayen.Dayen.repository.ProcesoRepository;
import jakarta.validation.Valid;
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

	public void createProceso(@Valid ProcesosRequest proceso){
		System.out.println(proceso.idLote());
		procesoRepository.insertProceso(proceso.idLote(), proceso.idTipo(),
				proceso.idProducto(), proceso.descripcion(), proceso.realizadoEn());
	}

	public Procesos updateProcesos(@Valid Procesos proceso){
		return this.procesoRepository.save(proceso);
	}
}

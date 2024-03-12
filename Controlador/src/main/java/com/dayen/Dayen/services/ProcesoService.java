package com.dayen.Dayen.services;

import com.dayen.Dayen.dao.request.ProcesoRequest;
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

	public Procesos createProceso(@Valid ProcesoRequest proceso){
		this.procesoRepository.insertProceso(proceso.idLote(), proceso.idTipo(),
				proceso.idProducto(), proceso.descripcion(), proceso.realizadoEn());
		return this.procesoRepository.findLastProceso();
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public Procesos updateProcesos(@Valid ProcesoRequest proceso){
		if (!this.procesoRepository.existsById(proceso.idProceso())) throw new RuntimeException("El Proceso no existe");
		this.procesoRepository.updateProceso(proceso.idProceso(), proceso.idLote(), proceso.idTipo(),
				proceso.idProducto(), proceso.descripcion(), proceso.realizadoEn());
		return this.procesoRepository.findById(proceso.idProceso()).get();
	}

	public void deleteProceso(@NotNull int idProceso){
		if (!this.procesoRepository.existsById(idProceso)) throw new RuntimeException("El proceso no existe");
		this.procesoRepository.deleteById(idProceso);
	}
}

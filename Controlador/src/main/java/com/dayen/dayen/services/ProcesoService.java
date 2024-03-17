package com.dayen.dayen.services;

import com.dayen.dayen.dao.request.ProcesoRequest;
import com.dayen.dayen.entity.Procesos;
import com.dayen.dayen.repository.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoService {
	private final ProcesoRepository procesoRepository;
	private final LoteRepository loteRepository;
	private final TipoProcesoRepository tipoProcesoRepository;
	private final ProductoRepository productoRepository;
	private final PersonalRepository personalRepository;

	public ProcesoService(ProcesoRepository procesoRepository, LoteRepository loteRepository, TipoProcesoRepository tipoProcesoRepository, ProductoRepository productoRepository, PersonalRepository personalRepository) {
		this.procesoRepository = procesoRepository;
		this.loteRepository = loteRepository;
		this.tipoProcesoRepository = tipoProcesoRepository;
		this.productoRepository = productoRepository;
		this.personalRepository = personalRepository;
	}

	public List<Procesos> getAllProcesosByLote(@NotNull Integer idLote) {
		return this.procesoRepository.findAllByIdLote(idLote);
	}

	public Procesos createProceso(@Valid ProcesoRequest proceso) {
		return this.procesoRepository.save(Procesos.builder()
				.idLote(this.loteRepository.findById(proceso.idLote())
						.orElseThrow(() -> new RuntimeException("El id del lote no existe")))
				.idTipo(this.tipoProcesoRepository.findById(proceso.idTipo()).orElseThrow())
				.idProducto(this.productoRepository.findAllById(proceso.idProducto()))
				.descripcion(proceso.descripcion())
				.realizadoEn(proceso.realizadoEn())
				.personal(this.personalRepository.findAllById(proceso.idPersonal()))
				.build());

	}

	public Procesos updateProcesos(@Valid ProcesoRequest proceso) {
		if (!this.procesoRepository.existsById(proceso.idProceso())) throw new RuntimeException("El Proceso no existe");
		return this.procesoRepository.save(Procesos.builder()
				.idProceso(proceso.idProceso())
				.idLote(this.loteRepository.findById(proceso.idLote())
						.orElseThrow(() -> new RuntimeException("El id del lote no existe")))
				.idTipo(this.tipoProcesoRepository.findById(proceso.idTipo()).orElseThrow())
				.idProducto(this.productoRepository.findAllById(proceso.idProducto()))
				.descripcion(proceso.descripcion())
				.realizadoEn(proceso.realizadoEn())
				.personal(this.personalRepository.findAllById(proceso.idPersonal()))
				.build());
	}

	public void deleteProceso(@NotNull int idProceso) {
		if (!this.procesoRepository.existsById(idProceso)) throw new RuntimeException("El proceso no existe");
		this.procesoRepository.deleteById(idProceso);
	}
}

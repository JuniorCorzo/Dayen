package com.dayen.Dayen.controller;

import com.dayen.Dayen.dao.ProcesoRequest;
import com.dayen.Dayen.entity.Procesos;
import com.dayen.Dayen.services.ProcesoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proceso")
public class ProcesosController {
	private final ProcesoService procesoService;

	public ProcesosController(ProcesoService procesoService) {
		this.procesoService = procesoService;
	}

	@GetMapping("/{idLote}")
	public List<Procesos> getAllProcesosByLote(@PathVariable Integer idLote) {
		return this.procesoService.getAllProcesosByLote(idLote);
	}

	@PostMapping("/create")
	public Procesos createProceso(@RequestBody ProcesoRequest proceso) {
		 return this.procesoService.createProceso(proceso);
	}

	@PutMapping("/update")
	public Procesos updateProceso(@RequestBody ProcesoRequest proceso) {
		return this.procesoService.updateProcesos(proceso);
	}

	@DeleteMapping("/delete/{id_proceso}")
	public void deleteProceso(@PathVariable("id_proceso") int idProceso){
		this.procesoService.deleteProceso(idProceso);
	}
}

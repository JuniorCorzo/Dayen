package com.dayen.Dayen.controller;

import com.dayen.Dayen.dao.ProcesosRequest;
import com.dayen.Dayen.entity.Procesos;
import com.dayen.Dayen.services.ProcesoService;
import org.springframework.http.MediaType;
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
	public void createProceso(@RequestBody ProcesosRequest proceso) {
		 this.procesoService.createProceso(proceso);
	}

	@PutMapping("/update")
	public Procesos updateProceso(@RequestBody Procesos proceso) {
		return this.procesoService.updateProcesos(proceso);
	}
}

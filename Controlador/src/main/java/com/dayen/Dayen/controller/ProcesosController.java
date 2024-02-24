package com.dayen.Dayen.controller;

import com.dayen.Dayen.entity.Procesos;
import com.dayen.Dayen.services.ProcesoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proceso")
public class ProcesosController {
	private final ProcesoService procesoService;

	public ProcesosController(ProcesoService procesoService) {
		this.procesoService = procesoService;
	}

	@GetMapping("/{idLote}")
	public List<Procesos> getAllProcesosByLote(@PathVariable Integer idLote){
		return procesoService.getAllProcesosByLote(idLote);
	}
}

package com.dayen.Dayen.controller;

import com.dayen.Dayen.entity.Lotes;
import com.dayen.Dayen.services.LotesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lotes")
public class LotesController {
	private final LotesService lotesService;

	public LotesController(LotesService lotesService) {
		this.lotesService = lotesService;
	}

	@GetMapping("/{idUsuario}")
	public List<Lotes> getAllLotesByUsuario(@PathVariable Integer idUsuario){
		return this.lotesService.getAllLotesByUsuario(idUsuario);
	}
}

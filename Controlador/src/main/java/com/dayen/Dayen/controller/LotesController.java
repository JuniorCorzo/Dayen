package com.dayen.Dayen.controller;

import com.dayen.Dayen.dao.LoteRequest;
import com.dayen.Dayen.entity.Lotes;
import com.dayen.Dayen.services.LotesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lotes")
public class LotesController {
	private final LotesService lotesService;

	public LotesController(LotesService lotesService) {
		this.lotesService = lotesService;
	}

	@GetMapping("/{idUsuario}")
	public List<Lotes> getAllLotesByUsuario(@PathVariable Integer idUsuario) {
		return this.lotesService.getAllLotesByUsuario(idUsuario);
	}

	@PostMapping("/create")
	public Lotes createLote(@RequestBody LoteRequest lote) {
		return this.lotesService.createLote(lote);
	}

	@PutMapping("/update")
	public Lotes updateLote(@RequestBody LoteRequest lote) {
		return this.lotesService.updateLote(lote);
	}

	@DeleteMapping("/delete/{id_lote}")
	public void deleteLote(@PathVariable("id_lote") int idLote){
		this.lotesService.deleteLote(idLote);
	}
}

package com.dayen.Dayen.controller;

import com.dayen.Dayen.entity.TipoProcesos;
import com.dayen.Dayen.services.TipoProcesoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipoProcesos")
public class TipoProcesoController {
	private final TipoProcesoService tipoProcesoService;

	public TipoProcesoController(TipoProcesoService tipoProcesoService) {
		this.tipoProcesoService = tipoProcesoService;
	}

	@GetMapping("/all")
	public List<TipoProcesos> getAllTipoProcesos(){
		return this.tipoProcesoService.getAllTipoProcesos();
	}
}

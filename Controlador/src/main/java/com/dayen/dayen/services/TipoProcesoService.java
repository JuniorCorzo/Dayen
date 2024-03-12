package com.dayen.dayen.services;

import com.dayen.dayen.entity.TipoProcesos;
import com.dayen.dayen.repository.TipoProcesoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProcesoService {
	private final TipoProcesoRepository tipoProcesoRepository;

	public TipoProcesoService(TipoProcesoRepository tipoProcesoRepository) {
		this.tipoProcesoRepository = tipoProcesoRepository;
	}

	public List<TipoProcesos> getAllTipoProcesos(){
		return this.tipoProcesoRepository.findAll();
	}
}

package com.dayen.Dayen.services;

import com.dayen.Dayen.entity.TipoProcesos;
import com.dayen.Dayen.repository.TipoProcesoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProcesoService {
	private final TipoProcesoRepository tipoProcesoRepository;

	public TipoProcesoService(TipoProcesoRepository tipoProcesoRepository) {
		this.tipoProcesoRepository = tipoProcesoRepository;
	}

	public List<TipoProcesos> getAllTipoProcesos(){
		return tipoProcesoRepository.findAll();
	}
}

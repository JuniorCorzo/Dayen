package com.dayen.Dayen.services;

import com.dayen.Dayen.entity.Procesos;
import com.dayen.Dayen.repository.ProcesoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoService {
	private final ProcesoRepository procesoRepository;

	public ProcesoService(ProcesoRepository procesoRepository) {
		this.procesoRepository = procesoRepository;
	}

	public List<Procesos> getAllProcesos(){
		return procesoRepository.findAll();
	}
}

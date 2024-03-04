package com.dayen.Dayen.services;

import com.dayen.Dayen.dao.LoteRequest;
import com.dayen.Dayen.entity.Lotes;
import com.dayen.Dayen.repository.LoteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotesService {
	private final LoteRepository loteRepository;

	public LotesService(LoteRepository loteRepository) {
		this.loteRepository = loteRepository;
	}

	public List<Lotes> getAllLotesByUsuario(@NotNull Integer idUsuario){
		return this.loteRepository.findAllByIdUsuario(idUsuario);
	}

	public Lotes createLote(@Valid LoteRequest lote){
		this.loteRepository.createLote(lote.idUsuario(),
				lote.fase(), lote.hectareas());

		return this.loteRepository.findLastLote();
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public Lotes updateLote(@Valid LoteRequest lote){
		if (!loteRepository.existsById(lote.idLote()))
			throw new RuntimeException("El lote no existe");

		this.loteRepository.updateLotes(lote.idLote(),
				lote.idUsuario(), lote.fase(), lote.hectareas());

		return this.loteRepository.findById(lote.idLote()).get();
	}

	public void deleteLote(int idLote){
		if(!loteRepository.existsById(idLote))
			throw new RuntimeException("El lote no existe");
		this.loteRepository.deleteById(idLote);
	}
}

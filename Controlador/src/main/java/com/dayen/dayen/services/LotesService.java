package com.dayen.dayen.services;

import com.dayen.dayen.dao.request.LoteRequest;
import com.dayen.dayen.entity.Lotes;
import com.dayen.dayen.exceptions.lote.LoteNotExists;
import com.dayen.dayen.repository.LoteRepository;
import com.dayen.dayen.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotesService {
	private final LoteRepository loteRepository;
	private final UsuarioRepository usuarioRepository;

	public LotesService(LoteRepository loteRepository, UsuarioRepository usuarioRepository) {
		this.loteRepository = loteRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public List<Lotes> getAllLotesByUsuario(@NotNull Integer idUsuario) {
		return this.loteRepository.findAllByIdUsuario(idUsuario);
	}

	public Lotes createLote(@Valid LoteRequest lote) {
		return this.loteRepository.save(Lotes.builder()
				.idUsuario(this.usuarioRepository.findById(lote.idUsuario()).orElseThrow())
				.nombre(lote.nombre())
				.tituloImagen(lote.tituloImagen())
				.fase(lote.fase())
				.hectareas(lote.hectareas())
				.build());
	}

	public Lotes updateLote(@Valid LoteRequest lote) {
		if (!loteRepository.existsById(lote.idLote()))
			throw new LoteNotExists();

		return this.loteRepository.save(Lotes.builder()
				.idLote(lote.idLote())
				.idUsuario(this.usuarioRepository.findById(lote.idUsuario()).orElseThrow())
				.nombre(lote.nombre())
				.tituloImagen(lote.tituloImagen())
				.fase(lote.fase())
				.hectareas(lote.hectareas())
				.build());
	}

	public void deleteLote(int idLote) {
		if (!loteRepository.existsById(idLote))
			throw new LoteNotExists();
		this.loteRepository.deleteById(idLote);
	}
}

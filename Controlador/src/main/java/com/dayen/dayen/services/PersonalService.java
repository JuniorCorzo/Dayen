package com.dayen.dayen.services;

import com.dayen.dayen.dao.request.PersonalRequest;
import com.dayen.dayen.entity.Personal;
import com.dayen.dayen.entity.Procesos;
import com.dayen.dayen.exceptions.personal.PersonalNotExists;
import com.dayen.dayen.repository.PersonalRepository;
import com.dayen.dayen.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {
	private final PersonalRepository personalRepository;
	private final UsuarioRepository usuarioRepository;

	public PersonalService(PersonalRepository personalRepository, UsuarioRepository usuarioRepository) {
		this.personalRepository = personalRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public List<Personal> getAllPersonalByUsuario(@NotNull Integer idUsuario) {
		return this.personalRepository.findAllByIdUsuario(idUsuario);
	}

	public List<Procesos> getAllProcesosOfPersonal(@NotNull Integer idPersonal){
		Personal personal = this.personalRepository.findById(idPersonal).orElseThrow(PersonalNotExists::new);
		return personal.getProcesos();
	}

	public Personal createPersonal(@Valid PersonalRequest personal) {
		return this.personalRepository.save(Personal.builder()
				.idUsuario(this.usuarioRepository.findById(personal.idUsuario()).orElseThrow())
				.nombre(personal.nombre())
				.telefono(personal.telefono())
				.build());
	}

	public Personal updatePersonal(@Valid PersonalRequest personal) {
		if (!this.personalRepository.existsById(personal.idPersonal()))
			throw new PersonalNotExists();

		return this.personalRepository.save(Personal.builder()
				.idPersonal(personal.idPersonal())
				.idUsuario(this.usuarioRepository.findById(personal.idUsuario()).orElseThrow())
				.nombre(personal.nombre())
				.telefono(personal.telefono())
				.build());
	}

	public void deletePersonal(Integer idPersonal) {
		if (!this.personalRepository.existsById(idPersonal))
			throw new PersonalNotExists();

		this.personalRepository.deleteById(idPersonal);
	}
}

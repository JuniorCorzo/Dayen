package com.dayen.Dayen.services;

import com.dayen.Dayen.dao.request.PersonalRequest;
import com.dayen.Dayen.entity.Personal;
import com.dayen.Dayen.repository.PersonalRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {
	private final PersonalRepository personalRepository;

	public PersonalService(PersonalRepository personalRepository) {
		this.personalRepository = personalRepository;
	}

	public List<Personal> getAllPersonalByUsuario(@NotNull Integer idUsuario){
		return this.personalRepository.findAllByIdUsuario(idUsuario);
	}

	public void createPersonal(@Valid PersonalRequest personal){
		this.personalRepository.createPersonal(personal.idUsuario(),
				personal.nombre(), personal.telefono());
	}

	public void updatePersonal(@Valid PersonalRequest personal){
		if (!this.personalRepository.existsById(personal.idPersonal()))
			throw new RuntimeException("El personal no existe");

		this.personalRepository.updatePersonal(personal.idPersonal(),
				personal.idUsuario(), personal.nombre(), personal.telefono());
	}

	public void deletePersonal(Integer idPersonal){
		if (!this.personalRepository.existsById(idPersonal))
			throw new RuntimeException("El personal no existe");

		this.personalRepository.deleteById(idPersonal);
	}
}

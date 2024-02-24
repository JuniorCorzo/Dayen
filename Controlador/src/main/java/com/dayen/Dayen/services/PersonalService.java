package com.dayen.Dayen.services;

import com.dayen.Dayen.entity.Personal;
import com.dayen.Dayen.repository.PersonalRepository;
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
		return personalRepository.findAllByIdUsuario(idUsuario);
	}
}

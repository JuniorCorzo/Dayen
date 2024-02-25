package com.dayen.Dayen.controller;

import com.dayen.Dayen.entity.Personal;
import com.dayen.Dayen.services.PersonalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personal")
public class PersonalController {
	private final PersonalService personalService;

	public PersonalController(PersonalService personalService) {
		this.personalService = personalService;
	}

	@GetMapping("/{idUsuario}")
	public List<Personal> getAllPersonalByUsuario(@PathVariable Integer idUsuario){
		return this.personalService.getAllPersonalByUsuario(idUsuario);
	}
}

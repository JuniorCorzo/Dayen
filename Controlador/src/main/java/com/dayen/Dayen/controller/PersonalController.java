package com.dayen.Dayen.controller;

import com.dayen.Dayen.dao.PersonalRequest;
import com.dayen.Dayen.entity.Personal;
import com.dayen.Dayen.services.PersonalService;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/create")
	public void createPersonal(@RequestBody PersonalRequest personal){
		this.personalService.createPersonal(personal);
	}

	@PutMapping("/update")
	public void updatePersonal(@RequestBody PersonalRequest personal){
		this.personalService.updatePersonal(personal);
	}

	@DeleteMapping("/delete/{id_personal}")
	public void deletePersonal(@PathVariable("id_personal") int idPersonal){
		this.personalService.deletePersonal(idPersonal);
	}
}

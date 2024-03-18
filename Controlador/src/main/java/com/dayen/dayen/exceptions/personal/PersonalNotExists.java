package com.dayen.dayen.exceptions.personal;

public class PersonalNotExists extends RuntimeException {
	public PersonalNotExists() {
		super("El trabajador no se encuentra registrado");
	}
}

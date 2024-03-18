package com.dayen.dayen.exceptions.proceso;

public class ProcesoNotExists extends RuntimeException {
	public ProcesoNotExists() {
		super("El proceso no esta registrado");
	}
}

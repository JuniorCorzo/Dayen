package com.dayen.dayen.exceptions.tipoProceso;

public class TipoProcesoNotExists extends RuntimeException {
	public TipoProcesoNotExists() {
		super("El tipo no proceso esta registrado");
	}
}

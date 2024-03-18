package com.dayen.dayen.exceptions.lote;

public class LoteNotExists extends RuntimeException {
	public LoteNotExists() {
		super("El Lote no existe");
	}
}

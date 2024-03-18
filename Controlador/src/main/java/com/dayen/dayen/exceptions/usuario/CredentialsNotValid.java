package com.dayen.dayen.exceptions.usuario;

public class CredentialsNotValid extends RuntimeException {
	public CredentialsNotValid(){
		super("Contraseña o usuario no validas");
	}
}

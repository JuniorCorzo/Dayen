package com.dayen.dayen.exceptions.usuario;

public class UserExists extends RuntimeException{
	public UserExists(){
		super("El usuario ya existe");
	}
}

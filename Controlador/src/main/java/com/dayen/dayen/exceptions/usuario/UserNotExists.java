package com.dayen.dayen.exceptions.usuario;

public class UserNotExists extends RuntimeException{
	public UserNotExists(){
		super("El usuario no existe");
	}
}

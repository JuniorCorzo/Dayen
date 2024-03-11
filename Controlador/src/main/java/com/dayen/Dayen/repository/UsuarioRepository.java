package com.dayen.Dayen.repository;

import com.dayen.Dayen.entity.Usuarios;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, String> {

	Optional<Usuarios> findByUsername(@NotNull @NotEmpty String nombre);
	@Transactional
	@Query(value = """
				INSERT INTO usuarios (id_usuario, nombre, apellido, rol, correo, clave, token)
				VALUES (:id_usuario, :nombre, :apellido, :rol, :correo, :clave, :token)
			""", nativeQuery = true)
	@Modifying
	void insertUsuario(@Param("id_usuario") String idUsuario, @Param("nombre") String nombre,
					   @Param("apellido") String apellido, @Param("rol") String rol,
					   @Param("correo") String correo, @Param("clave") String clave,
					   @Param("token") String token);

	@Transactional
	@Query(value = """
				UPDATE usuarios
				SET nombre = :nombre, apellido = :apellido, rol = :rol,
					correo = :correo, clave = :clave, token = :token
				WHERE id_usuario = :id_usuario
			""", nativeQuery = true)
	@Modifying
	void updateUsuario(@Param("id_usuario") String idUsuario, @Param("nombre") String nombre,
					   @Param("apellido") String apellido, @Param("rol") String rol,
					   @Param("correo") String correo, @Param("clave") String clave,
					   @Param("token") String token);
}

package com.dayen.dayen.repository;

import com.dayen.dayen.entity.Usuarios;
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

	@Transactional
	@Query(value = """
			UPDATE usuarios
			SET clave = :clave
			WHERE token = :token
			""", nativeQuery = true)
	@Modifying
	void updateClaveRecuperacion(@Param("token") String token, @Param("clave") String clave);

	@Transactional
	@Query(value = """
			UPDATE usuarios
			SET token = :token
			WHERE correo = :correo
			""", nativeQuery = true)
	@Modifying
	void updateTokenRecuperacion(@Param("correo") String correo, @Param("token") String token);

	@Transactional
	@Query(value = """
	UPDATE usuarios
	SET token = null
	WHERE token = :token
	""", nativeQuery = true)
	@Modifying
	void resetToken(@Param("token") String token);

	boolean existsByToken(String token);


}

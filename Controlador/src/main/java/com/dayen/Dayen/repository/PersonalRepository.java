package com.dayen.Dayen.repository;

import com.dayen.Dayen.entity.Personal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
	@Query(
			value = "SELECT * FROM personal WHERE id_usuario=:idUsuario",
			nativeQuery = true
	)
	List<Personal> findAllByIdUsuario(@Param("idUsuario") Integer idUsuario);

	@Transactional
	@Query(value = """
		INSERT INTO personal(id_usuario, nombre, telefono)
		VALUES (:id_usuario, :nombre, :telefono)
	""", nativeQuery = true)
	@Modifying
	void createPersonal(@Param("id_usuario") String idUsuario, @Param("nombre") String nombre,
							@Param("telefono") Integer telefono);

	@Transactional
	@Query(value = """
		UPDATE personal
		SET id_usuario = :id_usuario, nombre = :nombre, telefono = :telefono
		WHERE id_personal = :id_personal
	""", nativeQuery = true)
	@Modifying
	void updatePersonal(@Param("id_personal") Integer idPersonal, @Param("id_usuario") String idUsuario, @Param("nombre") String nombre,
						@Param("telefono") Integer telefono);
}

package com.dayen.Dayen.repository;

import com.dayen.Dayen.entity.Personal;
import com.dayen.Dayen.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
	@Query(value = "SELECT * FROM personal WHERE id_usuario=:idUsuario", nativeQuery = true)
	List<Personal> findAllByIdUsuario(@Param("idUsuario") Integer idUsuario);
}

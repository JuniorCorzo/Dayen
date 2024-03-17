package com.dayen.dayen.repository;

import com.dayen.dayen.entity.Personal;
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

}

package com.dayen.dayen.repository;

import com.dayen.dayen.entity.Lotes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoteRepository extends JpaRepository<Lotes, Integer> {
	@Query(value = "SELECT * FROM lotes WHERE id_usuario = :idUsuario",
			nativeQuery = true
	)
	List<Lotes> findAllByIdUsuario(@Param("idUsuario") Integer idUsuario);
}

package com.dayen.dayen.repository;

import com.dayen.dayen.entity.Procesos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProcesoRepository extends JpaRepository<Procesos, Integer> {
	@Query(
			value = "SELECT * FROM procesos WHERE id_lote=:idLote",
			nativeQuery = true
	)
	List<Procesos> findAllByIdLote(@Param("idLote") Integer idLote);
}

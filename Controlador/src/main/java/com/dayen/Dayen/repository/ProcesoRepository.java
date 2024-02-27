package com.dayen.Dayen.repository;

import com.dayen.Dayen.entity.Procesos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcesoRepository extends JpaRepository<Procesos, Integer> {
	@Query(
			value = "SELECT * FROM procesos WHERE id_lote=:idLote",
			nativeQuery = true
	)
	List<Procesos> findAllByIdLote(@Param("idLote") Integer idLote);
}

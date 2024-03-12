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

	@Query(value = "SELECT * FROM procesos ORDER BY id_proceso DESC LIMIT 1", nativeQuery = true)
	Procesos findLastProceso();

	@Transactional
	@Query(value = """
				INSERT INTO procesos (id_lote, id_tipo, id_producto, descripcion, realizado_en)
				VALUES (:id_lote, :id_tipo, :id_producto, :descripcion, :realizado_en);
			""",
			nativeQuery = true)
	@Modifying
	void insertProceso(@Param("id_lote") Integer idLote, @Param("id_tipo") Integer idTipo,
					   @Param("id_producto") Integer idProducto, @Param("descripcion") String descripcion,
					   @Param("realizado_en") LocalDateTime realizadoEn
	);

	@Transactional
	@Query(value = """
			UPDATE procesos
			SET id_lote = :id_lote, id_tipo = :id_tipo, id_producto = :id_producto,
			descripcion = :descripcion, realizado_en = :realizado_en
			WHERE id_proceso = :id_proceso
			""",
			nativeQuery = true)
	@Modifying
	void updateProceso(@Param("id_proceso") Integer idProceso, @Param("id_lote") Integer idLote, @Param("id_tipo") Integer idTipo,
					   @Param("id_producto") Integer idProducto, @Param("descripcion") String descripcion,
					   @Param("realizado_en") LocalDateTime realizadoEn);
}

package com.dayen.Dayen.repository;

import com.dayen.Dayen.entity.Lotes;
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

	@Query(value = """
				SELECT * FROM lotes
				ORDER BY lotes.id_lote
				DESC LIMIT 1
			""", nativeQuery = true)
	Lotes findLastLote();

	@Transactional
	@Query(value = """
			INSERT INTO lotes (id_usuario, fase, hectareas)
			VALUES(:id_usuario, :fase, :hectareas)
			""", nativeQuery = true)
	@Modifying
	void createLote(@Param("id_usuario") String idUsuario, @Param("fase") String fase,
					 @Param("hectareas") Integer hectareas);

	@Transactional
	@Query(value = """
			UPDATE lotes
			SET id_usuario = :id_usuario, fase = :fase, hectareas = :hectareas
			WHERE id_lote = :id_lote
			""", nativeQuery = true)
	@Modifying
	void updateLotes(@Param("id_lote") Integer idLote, @Param("id_usuario") String idUsuario, @Param("fase") String fase,
					  @Param("hectareas") Integer hectareas);
}

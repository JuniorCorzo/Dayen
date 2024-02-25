package com.dayen.Dayen.repository;

import com.dayen.Dayen.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuarios, String> {
}

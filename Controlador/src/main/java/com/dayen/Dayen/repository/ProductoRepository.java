package com.dayen.Dayen.repository;

import com.dayen.Dayen.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Productos, Integer> {
}

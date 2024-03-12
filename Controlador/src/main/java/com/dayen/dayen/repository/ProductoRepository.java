package com.dayen.dayen.repository;

import com.dayen.dayen.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Productos, Integer> {
}

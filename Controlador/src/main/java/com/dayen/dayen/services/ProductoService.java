package com.dayen.dayen.services;

import com.dayen.dayen.entity.Productos;
import com.dayen.dayen.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
	private final ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public List<Productos> getAllProductos(){
		return this.productoRepository.findAll();
	}
}

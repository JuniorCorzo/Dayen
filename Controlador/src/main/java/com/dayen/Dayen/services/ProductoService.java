package com.dayen.Dayen.services;

import com.dayen.Dayen.entity.Productos;
import com.dayen.Dayen.repository.ProductoRepository;
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

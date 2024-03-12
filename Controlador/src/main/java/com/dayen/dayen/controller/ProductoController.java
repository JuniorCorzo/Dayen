package com.dayen.dayen.controller;

import com.dayen.dayen.entity.Productos;
import com.dayen.dayen.services.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {
	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping("/all")
	public List<Productos> getAllProductos(){
		return this.productoService.getAllProductos();
	}
}

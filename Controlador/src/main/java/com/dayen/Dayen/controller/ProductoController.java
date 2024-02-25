package com.dayen.Dayen.controller;

import com.dayen.Dayen.entity.Productos;
import com.dayen.Dayen.services.ProductoService;
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

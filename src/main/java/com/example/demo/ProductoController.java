package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	private final ProductoService productoService;
	
	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	// Obtener todos los productos 
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProducts() {
		List<Producto> productos = productoService.findAll();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	// Obtener un producto por ID
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
		Producto producto = productoService.findById(id);
		if (producto != null) {
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Crear un nuevo producto
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		Producto newProducto = productoService.save(producto);
		return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
	}

	// Actualizar un producto existente
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable String id, @RequestBody Producto producto) {
		Producto existingProducto = productoService.findById(id);
		if (existingProducto != null) {
			producto.setId(id);
			Producto updatedProducto = productoService.update(id, existingProducto);
			return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Eliminar un producto
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProducto(@PathVariable String id) {
		Producto existingProducto = productoService.findById(id);
		if (existingProducto != null) {
			productoService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Ruta con parámetros de consulta (query string)
	@GetMapping("/buscar")
	public ResponseEntity<List<Producto>> findByFilter(@RequestParam(required = false) String categoria,
			@RequestParam(required = false) String nombre, @RequestParam(defaultValue = "0") double precio, 
			@RequestParam(defaultValue = "0") int stock) {
		List<Producto> productos = (List<Producto>) findByFilter(categoria, nombre, precio, stock);
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}


	/*
	// Ruta que lee cabeceras HTTP
	@GetMapping("/auth")
	public ResponseEntity<Producto> getProductByToken(@RequestHeader("Authorization") String authToken) {
		Producto producto = productoService.findByAuthToken(authToken);
		if (producto != null) {
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	*/
}

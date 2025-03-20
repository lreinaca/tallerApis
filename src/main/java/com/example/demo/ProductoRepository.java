package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {
	// simular una base de datos con un map
	private final Map<String, Producto> baseDeDatos = new HashMap<>();

	// guardar un producto
	public Producto save(Producto producto) {
		baseDeDatos.put(producto.getId(), producto);
		return producto;
	}

	// encontrar un producto por id
	public Producto finById(String id) {
		return baseDeDatos.get(id);
	}

	// listar todos los productos
	public List<Producto> findAll() {
		return new ArrayList<>(baseDeDatos.values());
	}

	// eliminar un producto
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}

	// actualizar un producto
	public Producto update(Producto producto) {
		if (baseDeDatos.containsKey(producto.getId())) {
			baseDeDatos.put(producto.getId(), producto);
			return producto;
		}
		return null;
	}

	// encontrar un producto por filtros
	public List<Producto> findByFilter(String categoria,String nombre, double precio, int stock) {
		return baseDeDatos.values().stream()
				.filter(producto -> categoria == null || producto.getCategoria().contains(categoria))
				.filter(producto -> nombre == null || producto.getNombre().contains(nombre))
				.filter(producto -> precio == 0 || producto.getPrecio() == precio)
				.filter(producto -> stock == 0 || producto.getStock() == stock)
				.collect(Collectors.toList());
	}
	
	

}

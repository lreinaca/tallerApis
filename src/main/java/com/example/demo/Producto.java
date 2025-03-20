package com.example.demo;

import java.util.UUID;

public class Producto {

	private String categoria;
	private String nombre;
	private double precio;
	private int stock;
	private String id;

	public Producto() {
		 this.id = UUID.randomUUID().toString();
	}

	public Producto(String categoria, String nombre, Double precio, Integer stock) {
		this.id = UUID.randomUUID().toString();
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
      //getters and setters
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getId() {
	       return id;
	   }
	   public void setId(String id) {
	       this.id = id;
	   }
	
}

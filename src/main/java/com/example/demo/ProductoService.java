package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {
	private ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        // como no estamos usando bases de datos, inicializamos por defecto con algunos productos
        listadoDeProductos();
    }

    public void listadoDeProductos() {
        // instanciamos algunos productos
       Producto producto = new Producto("electronica","Producto 1", 100.0, 10);
       Producto producto1 = new Producto("hogar","Producto 2", 200.0, 20);
       Producto producto2 = new Producto("electronica","Producto 3", 300.0, 30);
       // guardamos los productos
         save(producto);
         save(producto1);
         save(producto2);

    }

    // crear un nuevo producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // obtener un producto por id
    public Producto findById(String id) {
        return productoRepository.finById(id);
    }

    // obtener todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // actualizar un producto
    public Producto update(String id, Producto producto) {
        return productoRepository.update(id, producto);
    }

    // eliminar un producto
    public void deleteById(String id) {
        productoRepository.deleteById(id);
    }

    // obtener un producto por nombre o categoria
    public List<Producto> findByFilter(String nombre, String categoria) {
        return productoRepository.findByFilter(nombre, categoria);
    }


}

package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        
        // Inicializamos algunos datos de ejemplo
        initSampleData();
    }
    
    private void initSampleData() {
        // Crear usuarios
        Usuario juan = new Usuario("Juan Pérez", "juan@example.com", 30);
        Usuario maria = new Usuario("María López", "maria@example.com", 25);
        Usuario carlos = new Usuario("Carlos Ruiz", "carlos@example.com", 40);
        
        // Guardar usuarios
        save(juan);
        save(maria);
        save(carlos);
        
        // Agregar permisos a Juan
        List<String> permisosJuan = new ArrayList<>();
        permisosJuan.add("LEER");
        permisosJuan.add("ESCRIBIR");
        permisosJuan.add("INACTIVO_ADMIN");
        usuarioRepository.setPermisos(juan.getId(), permisosJuan);
        
        // Agregar permisos a María
        List<String> permisosMaria = new ArrayList<>();
        permisosMaria.add("LEER");
        permisosMaria.add("ADMIN");
        usuarioRepository.setPermisos(maria.getId(), permisosMaria);
        
        // Agregar accesos a Juan
        Map<String, Object> acceso1 = new HashMap<>();
        acceso1.put("fecha", "2023-01-01");
        acceso1.put("ip", "192.168.1.1");
        acceso1.put("navegador", "Chrome");
        usuarioRepository.registrarAcceso(juan.getId(), acceso1);
        
        Map<String, Object> acceso2 = new HashMap<>();
        acceso2.put("fecha", "2023-01-02");
        acceso2.put("ip", "192.168.1.2");
        acceso2.put("navegador", "Firefox");
        usuarioRepository.registrarAcceso(juan.getId(), acceso2);
        
    }
    
    // Crear un nuevo usuario
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    // Obtener un usuario por ID
    public Usuario findById(String id) {
        return usuarioRepository.findById(id);
    }
    
    // Listar todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    
    // Actualizar un usuario
    public Usuario update(Usuario usuario) {
        return usuarioRepository.update(usuario);
    }
    
    // Eliminar un usuario
    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }
    
    // Buscar usuarios por filtros
    public List<Usuario> buscarPorFiltros(String nombre, String email, int edad) {
        return usuarioRepository.buscarPorFiltros(nombre, email, edad);
    }
    
    // Encontrar usuario por token de autorización
    public Usuario findByAuthToken(String authToken) {
        return usuarioRepository.findByAuthToken(authToken);
    }
    
    public List<Usuario> findByRole(String role) {
		return usuarioRepository.findByRole(role);
	}

    
}
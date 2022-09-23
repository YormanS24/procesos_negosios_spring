package com.procesos.negocio.yorman.repository;

import com.procesos.negocio.yorman.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

        List<Usuario> findAllByNombre(String nombre);
        List<Usuario> findAllByNombreAndApellidos(String Nombre,String apellidos);




}

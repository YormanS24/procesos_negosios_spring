package com.procesos.negocio.yorman.controllers;

import com.procesos.negocio.yorman.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UsuarioController {

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Yorman Jose");
        usuario.setApellidos("Carrascal Sanchez");
        usuario.setDocumento("1008963423");
        usuario.setFechaNacimiento(new Date(2001,12,01));
        usuario.setTelefono("3137644976");
        return usuario;
    }
}

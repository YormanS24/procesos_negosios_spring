package com.procesos.negocio.yorman.controllers;

import com.procesos.negocio.yorman.models.Usuario;
import com.procesos.negocio.yorman.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

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

    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepositorio.save(usuario);
        return usuario;
    }
}

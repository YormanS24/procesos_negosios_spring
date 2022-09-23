package com.procesos.negocio.yorman.controllers;

import com.procesos.negocio.yorman.models.Usuario;
import com.procesos.negocio.yorman.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return usuario;
    }

    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUusuarios(){
        return  usuarioRepositorio.findAll();
    }

    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public List<Usuario> listarPorNombreApellido(@PathVariable String nombre,@PathVariable String apellidos){
        return usuarioRepositorio.findAllByNombreAndApellidos(nombre,apellidos);
    }
    @GetMapping("/usuarios/apellido/{apellidos}")
    public List<Usuario> listarApellido(@PathVariable String apellidos){
        return usuarioRepositorio.findAllByApellidos(apellidos);
    }

    @GetMapping("/usuarios/nombre/{nombre}")
    public List<Usuario> listarPorNombre(@PathVariable String nombre) {
        return usuarioRepositorio.findAllByApellidos(nombre);
    }

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioBD =  usuarioRepositorio.findById(id).get();
        try {
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioRepositorio.save(usuarioBD);
            return usuarioBD;
        }catch (Exception ex){
            return null;
        }
    }

    @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBD = usuarioRepositorio.findById(id).get();
        try {
            usuarioRepositorio.delete(usuarioBD);
            return usuarioBD;
        }catch (Exception ex){
            return  null;
        }
    }
}

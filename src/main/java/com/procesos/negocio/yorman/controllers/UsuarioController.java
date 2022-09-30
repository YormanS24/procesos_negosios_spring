package com.procesos.negocio.yorman.controllers;

import com.procesos.negocio.yorman.models.Usuario;
import com.procesos.negocio.yorman.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario){
        try {
            usuarioRepositorio.save(usuario);
            return new ResponseEntity(usuario,HttpStatus.CREATED);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUusuarios(){
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public  ResponseEntity listarPorNombreApellido(@PathVariable String nombre,@PathVariable String apellidos){
        List<Usuario> usuarios = usuarioRepositorio.findAllByNombreAndApellidos(nombre,apellidos);
        if (usuarios.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @GetMapping("/usuarios/apellido/{apellidos}")
    public ResponseEntity listarApellido(@PathVariable String apellidos){
        List<Usuario> usuarios = usuarioRepositorio.findAllByApellidos(apellidos);
        if (usuarios.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre) {
        List<Usuario> usuarios = usuarioRepositorio.findAllByNombre(nombre);
        if (usuarios.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> usuarioBD =  usuarioRepositorio.findById(id);
        if (usuarioBD.isPresent()){
            try {
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellidos(usuario.getApellidos());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                usuarioRepositorio.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD,HttpStatus.OK);
            }catch (Exception ex){
                return ResponseEntity.badRequest().build();
            }

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioBD = usuarioRepositorio.findById(id);
        if (usuarioBD.isPresent()){
            usuarioRepositorio.delete(usuarioBD.get());
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

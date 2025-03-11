package com.projectmg.Resources;

import com.projectmg.DTO.UsuarioDto;
import com.projectmg.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mg/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuarioDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioDto));
    }

    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorEmail(@PathVariable String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }
}

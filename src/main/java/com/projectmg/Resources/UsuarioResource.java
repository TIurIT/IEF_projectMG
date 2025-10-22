package com.projectmg.Resources;

import com.projectmg.Dtos.UsuarioDTO;
import com.projectmg.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mg/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO = usuarioService.cadastrarUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioDTO));
    }

    @GetMapping("/b/email/{email}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@PathVariable String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }
}

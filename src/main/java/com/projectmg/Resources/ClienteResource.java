package com.projectmg.Resources;

import com.projectmg.Dto.ClienteDTO;
import com.projectmg.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping({"/",""})
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteService.buscarClienteTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarClientePorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(clienteDTO));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.atualizarCliente(clienteDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ClienteDTO> deletarCliente(@PathVariable Long id) {
       clienteService.deletarCliente(id);
       return  ResponseEntity.noContent().build();
    }

    @GetMapping("/b/nome/{nome}")
    public ResponseEntity<List<ClienteDTO>> buscarClientePorNome(@PathVariable String nome) {
        return ResponseEntity.ok(clienteService.buscarClientePorNome(nome));
    }
}

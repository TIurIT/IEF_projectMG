package Resources;

import DTO.SublimacaoDto;
import Services.SublimacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mg/sublimacaos")
public class SublimacaoResource {

    @Autowired
    private SublimacaoService sublimacaoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<SublimacaoDto> buscarSublimacaoPorId(@PathVariable UUID id){
        return ResponseEntity.ok(sublimacaoService.buscarSublimacaoPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SublimacaoDto> cadastrarSublimacao(@RequestBody SublimacaoDto sublimacaoDto){
        return ResponseEntity.ok(sublimacaoService.cadastrarSublimacao(sublimacaoDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarSublimacao(@PathVariable UUID id) {
        sublimacaoService.deletarSublimacao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SublimacaoDto> atualizarSublimacao(@PathVariable UUID id, @RequestBody SublimacaoDto sublimacaoDto){
        return ResponseEntity.ok(sublimacaoService.atualizarSublimacao(sublimacaoDto));
    }

    @GetMapping("/buscar/bairro/{bairro}")
    public ResponseEntity<SublimacaoDto> buscarSublimacaoPorBairro(@PathVariable String bairro){
        return ResponseEntity.ok(sublimacaoService.buscarSublimacaoPorBairro(bairro));
    }
}

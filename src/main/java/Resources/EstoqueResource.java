package Resources;

import DTO.EstoqueDto;
import Services.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/mg/estoques")
public class EstoqueResource {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorId(@PathVariable UUID id){
        return ResponseEntity.ok(estoqueService.buscarEstoque(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EstoqueDto> cadastrarEstoque(@RequestBody EstoqueDto estoqueDto){
        EstoqueDto estoque = estoqueService.cadastrarEstoque(estoqueDto);
        return ResponseEntity.ok(estoqueService.cadastrarEstoque(estoque));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable UUID id) {
        estoqueService.excluirEstoque(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EstoqueDto> atualizarEstoque(@PathVariable UUID id, @RequestBody EstoqueDto estoqueDto){
        return ResponseEntity.ok(estoqueService.atualizarEstoque(estoqueDto));
    }

    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorTipo(tipo));
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorNome(@PathVariable String nome){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorNome(nome));
    }

    @GetMapping("/buscar/marca/{marca}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorMarca(@PathVariable String marca){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorMarca(marca));
    }
}

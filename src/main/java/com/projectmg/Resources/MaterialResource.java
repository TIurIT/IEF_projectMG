package com.projectmg.Resources;

import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mg/estoque")
public class MaterialResource {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<MaterialDTO> buscarMaterialPorId(@PathVariable Long id){
        return ResponseEntity.ok(materialService.buscarMaterial(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MaterialDTO> cadastrarMaterial(@RequestBody MaterialDTO MaterialDto){
        MaterialDTO material = materialService.cadastrarMaterial(MaterialDto);
        return ResponseEntity.ok(materialService.cadastrarMaterial(material));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMaterial(@PathVariable Long id) {
        materialService.excluirMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MaterialDTO> atualizarMaterial(@PathVariable Long id, @RequestBody MaterialDTO MaterialDto){
        return ResponseEntity.ok(materialService.atualizarMaterial(MaterialDto));
    }


    @GetMapping("/b/nome/{nome}")
    public ResponseEntity<List<MaterialDTO>> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(materialService.buscarPorNome(nome));
    }

    @GetMapping("/b/tipo/{tipo}")
    public ResponseEntity<List<MaterialDTO>> buscarPorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(materialService.buscarPorTipo(tipo));
    }

    @GetMapping("/b/marca/{marca}")
    public ResponseEntity<List<MaterialDTO>> buscarPorMarca(@PathVariable String marca){
        return ResponseEntity.ok(materialService.buscarPorMarca(marca));
    }
}

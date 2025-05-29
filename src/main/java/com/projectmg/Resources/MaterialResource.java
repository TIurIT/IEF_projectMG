package com.projectmg.Resources;

import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Models.Material;
import com.projectmg.Repositories.MaterialRepository;
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

    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping({"/",""})
    public ResponseEntity<List<MaterialDTO>> buscarTodosMateriais() {
        return ResponseEntity.ok(materialService.buscarMaterialTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<MaterialDTO> buscarMaterialPorId(@PathVariable Long id){
        return ResponseEntity.ok(materialService.buscarMaterialPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MaterialDTO> cadastrarMaterial(@RequestBody MaterialDTO materialDTO){
        MaterialDTO material = materialService.cadastrarMaterial(materialDTO);
        return ResponseEntity.ok(material);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMaterial(@PathVariable Long id) {
        materialService.deletarMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MaterialDTO> atualizarMaterial(@PathVariable Long id, @RequestBody MaterialDTO materialDTO){
        materialDTO.setId(id);
        return ResponseEntity.ok(materialService.atualizarMaterial(materialDTO));
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

    @GetMapping("/ultimos")
    public List<Material> ultimosMaterial(){
        return materialRepository.findTop5ByOrderByDataAtualizacaoDesc();
    }
}

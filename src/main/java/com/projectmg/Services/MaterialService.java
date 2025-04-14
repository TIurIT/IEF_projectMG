package com.projectmg.Services;

import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Models.Material;
import com.projectmg.Repositories.MaterialRepository;
import com.projectmg.Specs.MaterialSpec;
import com.projectmg.Exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MaterialService {

    private static final String MSG_ESTOQUE = "Material não encontrado";
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialSpec materialSpec;

    public Material converterMaterialDtoParaMaterial(MaterialDTO materialDTO){
        Material material = new Material();
        material.setId(materialDTO.getId());
        material.setTipo(materialDTO.getTipo());
        material.setNome(materialDTO.getNome());
        material.setMarca(materialDTO.getMarca());
        material.setQuantidade(materialDTO.getQuantidade());
        material.setDataDeCriacao(materialDTO.getDataDeCriacao());
        return material;
    }

    public MaterialDTO converterMaterialParaMaterialDto(Material material){
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(material.getId());
        materialDTO.setTipo(material.getTipo());
        materialDTO.setNome(material.getNome());
        materialDTO.setMarca(material.getMarca());
        materialDTO.setQuantidade(material.getQuantidade());
        materialDTO.setDataDeCriacao(material.getDataDeCriacao());
        return materialDTO;
    }

    public MaterialDTO cadastrarMaterial(MaterialDTO materialDTO) {
        materialSpec.verifyMaterialNome(materialDTO.getNome());
        List<Material> materialNome = materialRepository.findByNome(materialDTO.getNome());
        materialSpec.verifyMaterialNomeExists(materialNome);
        materialSpec.verifyMaterialTipo(materialDTO.getTipo());
        materialSpec.verigyMaterialMarca(materialDTO.getMarca());

        Material material = converterMaterialDtoParaMaterial(materialDTO);
        material = materialRepository.save(material);
        return converterMaterialParaMaterialDto(material);
    }

    public MaterialDTO atualizarMaterial(MaterialDTO materialDTO) {
        List<Material> materialNome = materialRepository.findByNome(materialDTO.getNome());
        materialSpec.verifyMaterialNomeExists(materialNome);
        materialSpec.verifyMaterialId(materialDTO.getId());

        Material material = materialRepository.findById(materialDTO.getId())
                        .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
        material = converterMaterialDtoParaMaterial(materialDTO);
        materialRepository.save(material);
        return  converterMaterialParaMaterialDto(material);
    }

    public void deletarMaterial(Long id) {
        materialRepository.deleteById(id);
    }

    public MaterialDTO buscarMaterial(Long id) {
       Material material = materialRepository.findById(id)
               .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
       return converterMaterialParaMaterialDto(material);
    }

    public List<MaterialDTO> buscarPorNome(String nome) {
        List<Material> materiais = materialRepository.findByNome(nome);
        materialSpec.verifyMaterial(materiais);
        List<MaterialDTO> dtos = new java.util.ArrayList<>();
        materiais.forEach(material -> {
            dtos.add(converterMaterialParaMaterialDto(material));
        });

        return dtos;
    }

    public List<MaterialDTO> buscarPorTipo(String tipo) {
        List<Material> materiais = materialRepository.findByTipo(tipo);
        materialSpec.verifyMaterial(materiais);
        List<MaterialDTO> dtos = new java.util.ArrayList<>();
        materiais.forEach(material -> {
            dtos.add(converterMaterialParaMaterialDto(material));
        });

        return dtos;
    }

    public List<MaterialDTO> buscarPorMarca(String marca) {
        List<Material> materiais = materialRepository.findByMarca(marca);
        materialSpec.verifyMaterial(materiais);
        List<MaterialDTO> dtos = new java.util.ArrayList<>();
        materiais.forEach(material -> {
            dtos.add(converterMaterialParaMaterialDto(material));
        });

        return dtos;
    }

}

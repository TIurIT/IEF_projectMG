package com.projectmg.Services;

import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Models.Material;
import com.projectmg.Repositories.MaterialRepository;
import com.projectmg.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MaterialService {

    private static final String MSG_ESTOQUE = "Produto não encontrado";
    @Autowired
    private MaterialRepository materialRepository;

    public Material converterMaterialDtoParaMaterial(MaterialDTO MaterialDto){
        Material material = new Material();
        material.setId(MaterialDto.getId());
        material.setTipo(MaterialDto.getTipo());
        material.setNome(MaterialDto.getNome());
        material.setMarca(MaterialDto.getMarca());
        material.setQuantidade(MaterialDto.getQuantidade());
        material.setDataDeCriacao(MaterialDto.getDataDeCriacao());
        return material;
    }

    public MaterialDTO converterMaterialParaMaterialDto(Material material){
        MaterialDTO MaterialDto = new MaterialDTO();
        MaterialDto.setId(material.getId());
        MaterialDto.setTipo(material.getTipo());
        MaterialDto.setNome(material.getNome());
        MaterialDto.setMarca(material.getMarca());
        MaterialDto.setQuantidade(material.getQuantidade());
        MaterialDto.setDataDeCriacao(material.getDataDeCriacao());
        return MaterialDto;
    }

    public MaterialDTO cadastrarMaterial(MaterialDTO MaterialDto) {
        Material material = converterMaterialDtoParaMaterial(MaterialDto);
        material = materialRepository.save(material);
        return converterMaterialParaMaterialDto(material);
    }

    public MaterialDTO atualizarMaterial(MaterialDTO MaterialDto) {
        Material material = materialRepository.findById(MaterialDto.getId())
                        .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
        material = converterMaterialDtoParaMaterial(MaterialDto);
        materialRepository.save(material);
        return  converterMaterialParaMaterialDto(material);
    }

    public void excluirMaterial(Long id) {
        materialRepository.deleteById(id);
    }

    public MaterialDTO buscarMaterial(Long id) {
       Material material = materialRepository.findById(id)
               .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
       return converterMaterialParaMaterialDto(material);
    }

    public List<Material> buscarPorNome(String nome) {
        return materialRepository.findByNome(nome);
    }

    public List<Material> buscarPorTipo(String tipo) {
        return materialRepository.findByTipo(tipo);
    }

    public List<Material> buscarPorMarca(String marca) {
        return materialRepository.findByMarca(marca);
    }

}

package com.projectmg.Domain.Validation;

import com.projectmg.Domain.Entity.Material.Material;
import com.projectmg.Exception.BusinessException;
import com.projectmg.Repository.Material.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class MaterialSpec {

    @Autowired
    private MaterialRepository materialRepository;

    private static final String MSG_MATERIAL = "Material não encontrado";
    private static final String MSG_MATERIAL_NOME = "Nome já cadastrado";
    private static final String MSG_MATERIAL_NullNOME = "Nome do material não pode ser vazio";
    private static final String MSG_MATERIAL_MARCA = "Marca do material não pode ser vazia";
    private static final String MSG_MATERIAL_TIPO = "Tipo do material não pode ser vazio";


    public void verifyMaterialNomeExists(List<Material> materiais) {
        if (materiais.size() > 0) {
            throw new BusinessException(MSG_MATERIAL_NOME);
        }
    }

    public void verifyMaterialNome(String nome) {
        if (nome.isEmpty()) {
            throw new BusinessException(MSG_MATERIAL_NullNOME);
        }
    }

    public void verifyMaterialTipo(String tipo) {
        if (isNull(tipo)){
            throw new BusinessException(MSG_MATERIAL_TIPO);
        }
    }

    public void verigyMaterialMarca(String marca) {
        if (isNull(marca)){
            throw new BusinessException(MSG_MATERIAL_MARCA);
        }
    }

    public void verifyMaterialId(Long id) {
        if (isNull(id)) {
            throw new BusinessException(MSG_MATERIAL);
        }
    }

    public void verifyMaterial(List<Material> materiais) {
        if (materiais.isEmpty()) {
            throw new BusinessException(MSG_MATERIAL);
        }
    }

    public void verifyProdutoNomeDup(String nome, Long id) {
        if (materialRepository.existsByNomeAndIdNot(nome, id)){
            throw new BusinessException(MSG_MATERIAL_NOME);
        }
    }


}


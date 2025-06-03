package com.projectmg.Services;

import com.projectmg.Dto.HistoricoMaterialDTO;
import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.HistoricoMaterial;
import com.projectmg.Models.Material;
import com.projectmg.Repositories.HistoricoMaterialRepository;
import com.projectmg.Repositories.MaterialRepository;
import com.projectmg.Security.UsuarioAuditoria;
import com.projectmg.Specs.MaterialSpec;
import com.projectmg.Exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class MaterialService {

    private static final String MSG_ESTOQUE = "Material não encontrado";

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialSpec materialSpec;

    @Autowired
    private HistoricoMaterialRepository historicoRepository;

    public Material converterMaterialDtoParaMaterial(MaterialDTO materialDTO){
        Material material = new Material();
        material.setId(materialDTO.getId());
        material.setTipo(materialDTO.getTipo());
        material.setNome(materialDTO.getNome());
        material.setMarca(materialDTO.getMarca());
        material.setQuantidade(materialDTO.getQuantidade());
        material.setDataDeCriacao(materialDTO.getDataDeCriacao());
        material.setDataAtualizacao(materialDTO.getDataAtualizacao());
        material.setUsuarioUltimaAlteracao(materialDTO.getUsuarioUltimaAlteracao());
        material.setAcao(materialDTO.getAcao());
        material.setComentario(materialDTO.getComentario());

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
        materialDTO.setDataAtualizacao(material.getDataAtualizacao());
        materialDTO.setUsuarioUltimaAlteracao(material.getUsuarioUltimaAlteracao());
        materialDTO.setAcao(material.getAcao());
        materialDTO.setComentario(material.getComentario());

        return materialDTO;
    }

    public MaterialDTO cadastrarMaterial(MaterialDTO materialDTO) {
        materialSpec.verifyMaterialNome(materialDTO.getNome());
        List<Material> materialNome = materialRepository.findByNome(materialDTO.getNome());
        materialSpec.verifyMaterialNomeExists(materialNome);
        materialSpec.verifyMaterialTipo(materialDTO.getTipo());
        materialSpec.verigyMaterialMarca(materialDTO.getMarca());
        Material material = converterMaterialDtoParaMaterial(materialDTO);
        material.setAcao(material.getId() == null ? TipoAcao.CRIADO : TipoAcao.ATUALIZADO);
        material = materialRepository.save(material);

        return converterMaterialParaMaterialDto(material);
    }

    public MaterialDTO atualizarMaterial(MaterialDTO materialDTO) {
        materialSpec.verifyMaterialId(materialDTO.getId());
        Material materialExistente = materialRepository.findById(materialDTO.getId())
                .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
        materialSpec.verifyProdutoNomeDup(materialDTO.getNome(),materialDTO.getId());
        materialExistente.setNome(materialDTO.getNome());
        materialExistente.setMarca( materialDTO.getMarca());
        materialExistente.setTipo(materialDTO.getTipo());
        materialExistente.setQuantidade(materialDTO.getQuantidade());
        materialExistente.setDataDeCriacao(materialDTO.getDataDeCriacao());
        materialExistente.setDataAtualizacao(LocalDate.now());
        materialExistente.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        materialExistente.setAcao(TipoAcao.ATUALIZADO);
        materialExistente.setComentario(materialDTO.getComentario());
        materialRepository.save(materialExistente);

        return  converterMaterialParaMaterialDto(materialExistente);
    }

    public void deletarMaterial(Long id) {
        Material material = materialRepository.findById(id).orElseThrow();
        material.setAcao(TipoAcao.DELETADO);
        material.setDataAtualizacao(LocalDate.now());
        material.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        materialRepository.deleteById(id);
    }

    public MaterialDTO buscarMaterialPorId(Long id) {
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

    public List<MaterialDTO> buscarMaterialTodos(){
        List<Material> materiais = materialRepository.findAllAtivos();
        materialSpec.verifyMaterial(materiais);
        List<MaterialDTO> dtos = new java.util.ArrayList<>();
        materiais.forEach(material -> {
            dtos.add(converterMaterialParaMaterialDto(material));
        });

        return dtos;
    }

    public HistoricoMaterialDTO converterHistoricoParaDTO(HistoricoMaterial historico) {
        HistoricoMaterialDTO dto = new HistoricoMaterialDTO();
        dto.setId(historico.getId());
        dto.setMaterialId(historico.getMaterial().getId());
        dto.setMaterialNome(historico.getMaterial().getNome());
        dto.setQuantidade(historico.getQuantidade());
        dto.setComentario(historico.getComentario());
        dto.setDataHistorico(historico.getDataHistorico());
        dto.setAcao(historico.getAcao());
        dto.setUsuarioUltimaAlteracao(historico.getUsuarioUltimaAlteracao());
        return dto;
    }

    private void salvarHistorico(Material material, Integer quantidade, String comentario, TipoAcao acao) {
        HistoricoMaterial historico = new HistoricoMaterial();
        historico.setMaterial(material);
        historico.setQuantidade(quantidade);
        historico.setComentario(comentario);
        historico.setDataHistorico(LocalDate.now());
        historico.setAcao(acao);
        historico.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());

        historicoRepository.save(historico);
    }


    public MaterialDTO adicionarQuantidade(Long id, Integer quantidade, String comentario){
        Material material = materialRepository.findById(id).orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
        material.setQuantidade(material.getQuantidade() + quantidade);
        material.setComentario(comentario);
        material.setDataAtualizacao(LocalDate.now());
        material.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        material.setAcao(TipoAcao.ATUALIZADO);
        materialRepository.save(material);

        salvarHistorico(material, quantidade, comentario, TipoAcao.ADICIONADO);

        return converterMaterialParaMaterialDto(material);
    }
    public MaterialDTO retirarQuantidade(Long id, Integer quantidade, String comentario){
        Material material = materialRepository.findById(id).orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
        material.setQuantidade(material.getQuantidade() - quantidade);
        material.setComentario(comentario);
        material.setDataAtualizacao(LocalDate.now());
        material.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        material.setAcao(TipoAcao.ATUALIZADO);
        materialRepository.save(material);

        salvarHistorico(material, quantidade, comentario, TipoAcao.DELETADO);

        return converterMaterialParaMaterialDto(material);
    }
}

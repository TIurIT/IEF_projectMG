package com.projectmg.Services;

import com.projectmg.Dtos.MaterialDTO;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.Material;
import com.projectmg.Models.HistoricoMaterial;
import com.projectmg.Repositories.HistoricoMaterialRepository;
import com.projectmg.Repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private HistoricoMaterialRepository historicoRepository;


    public List<MaterialDTO> listarTodos() {
        return materialRepository.findAll().stream()
                .map(MaterialDTO::fromEntity)
                .toList();
    }

    public List<MaterialDTO> listarAtivos() {
        return materialRepository.findAllAtivos().stream()
                .map(MaterialDTO::fromEntity)
                .toList();
    }

    public List<MaterialDTO> listarUltimos() {
        List<Material> materiais = materialRepository.findTop5ByOrderByDataAtualizacaoDesc();
        return materiais.stream()
                .map(MaterialDTO::fromEntity)
                .toList();
    }

    private MaterialDTO toDTOComUltimoComentario(Material m) {
        String ultimoComentario = null;
        if (m.getHistoricos() != null && !m.getHistoricos().isEmpty()) {
            HistoricoMaterial ultimo = m.getHistoricos()
                    .stream()
                    .max(Comparator.comparing(HistoricoMaterial::getDataAtualizacao))
                    .orElse(null);
            if (ultimo != null) {
                ultimoComentario = ultimo.getComentario();
            }
        }

        return new MaterialDTO(
                m.getId(),
                m.getNome(),
                m.getTipo(),
                m.getFornecedor(),
                m.getQuantidade(),
                m.getRendimento(),
                m.getTotalDePecas(),
                m.getDataDeCriacao(),
                m.getDataAtualizacao(),
                m.getAcao(),
                m.getUsuarioUltimaAlteracao(),
                ultimoComentario,
                m.getLimiteMinimo(),
                m.isAtivo()
                m.getDataProgramadaCompra()
        );
    }

    public MaterialDTO cadastrarMaterial(MaterialDTO dto, String usuario) {
        Material material = new Material();
        material.setNome(dto.nome());
        material.setTipo(dto.tipo());
        material.setFornecedor(dto.fornecedor());
        material.setQuantidade(dto.quantidade());
        material.setRendimento(dto.rendimento());
        material.setUsuarioUltimaAlteracao(usuario);
        material.setDataDeCriacao(LocalDateTime.now());
        material.setDataAtualizacao(LocalDateTime.now());

        // cálculo automático do total de peças
        if (dto.rendimento() != null && dto.quantidade() != null) {
            material.setTotalDePecas(dto.rendimento() * dto.quantidade());
        }

        Material salvo = materialRepository.save(material);

        registrarHistorico(salvo, TipoAcao.CRIADO, dto.quantidade(), usuario, "Cadastro inicial");

        return toDTOComUltimoComentario(salvo);
    }

    public MaterialDTO atualizarMaterial(Long id, MaterialDTO dto, String usuario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        material.setNome(dto.nome());
        material.setTipo(dto.tipo());
        material.setFornecedor(dto.fornecedor());
        material.setQuantidade(dto.quantidade());
        material.setRendimento(dto.rendimento());

        // Recalcular total de peças
        if (dto.rendimento() != null && dto.quantidade() != null) {
            material.setTotalDePecas(dto.rendimento() * dto.quantidade());
        }

        material.setAcao(TipoAcao.ATUALIZADO);
        material.setUsuarioUltimaAlteracao(usuario);
        material.setDataAtualizacao(LocalDateTime.now());

        Material salvo = materialRepository.save(material);

        String comentario = dto.ultimoComentario() != null ? dto.ultimoComentario() : "Atualização manual";
        registrarHistorico(salvo, TipoAcao.ATUALIZADO, dto.quantidade(), usuario, comentario);

        return MaterialDTO.fromEntity(salvo);
    }

    public void deletarMaterial(Long id, String usuario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        material.setQuantidade(0.0);
        material.setTotalDePecas(0.0);
        materialRepository.save(material);

        HistoricoMaterial historico = new HistoricoMaterial();
        historico.setMaterial(material);
        historico.setAcao(TipoAcao.DELETADO);
        historico.setQuantidadeAlterada(0.0);
        historico.setUsuarioUltimaAtualizacao(usuario);
        historico.setComentario("Material deletado");
        historicoRepository.save(historico);

        material.setAtivo(false);
        materialRepository.save(material);
    }

    public MaterialDTO atualizarQuantidade(Long id, Double quantidade, TipoAcao acao, String usuario, String comentario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        if (acao == TipoAcao.ADICIONADO) {
            material.setQuantidade(material.getQuantidade() + quantidade);
        } else if (acao == TipoAcao.RETIRADO) {
            material.setQuantidade(material.getQuantidade() - quantidade);
        }
        if (material.getRendimento() != null && material.getQuantidade() != null) {
            material.setTotalDePecas(material.getRendimento() * material.getQuantidade());
        }
        if (material.getLimiteMinimo() != null && material.getTotalDePecas() >= material.getLimiteMinimo()) {
            material.setDataProgramadaCompra(null);
        }

        material.setAcao(acao);
        material.setUsuarioUltimaAlteracao(usuario);
        material.setDataAtualizacao(LocalDateTime.now());

        Material salvo = materialRepository.save(material);

        registrarHistorico(salvo, acao, quantidade, usuario, comentario);

        return toDTOComUltimoComentario(salvo);
    }

    private void registrarHistorico(Material material, TipoAcao acao, Double quantidade, String usuario, String comentario) {
        HistoricoMaterial historico = new HistoricoMaterial();
        historico.setMaterial(material);
        historico.setAcao(acao);
        historico.setQuantidadeAlterada(quantidade);
        historico.setUsuarioUltimaAtualizacao(usuario);
        historico.setComentario(comentario);
        historico.setDataAtualizacao(LocalDateTime.now());
        historicoRepository.save(historico);
    }

    public MaterialDTO definirLimite(Long materialId, Double limiteMinimo) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        material.setLimiteMinimo(limiteMinimo);

        Material atualizado = materialRepository.save(material);

        return MaterialDTO.fromEntity(atualizado);
    }

    public MaterialDTO reativarMaterial(Long id, String usuario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        if (material.isAtivo()) {
            throw new RuntimeException("O material já está ativo.");
        }

        material.setAtivo(true);
        material.setDataAtualizacao(LocalDateTime.now());
        material.setUsuarioUltimaAlteracao(usuario);
        material.setAcao(TipoAcao.ATUALIZADO);

        Material salvo = materialRepository.save(material);

        registrarHistorico(salvo, TipoAcao.ATUALIZADO, 0.0, usuario, "Material Voltando ao Estoque");

        return MaterialDTO.fromEntity(salvo);
    }

    public Material programarCompra(Long id, String data, String usuario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));
        material.setDataProgramadaCompra(LocalDate.parse(data));
        material.setDataAtualizacao(LocalDateTime.now());
        material.setUsuarioUltimaAlteracao(usuario);
        return materialRepository.save(material);
    }

}

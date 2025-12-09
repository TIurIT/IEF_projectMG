package com.projectmg.Services;

import com.projectmg.Dtos.MaterialDTO;
import com.projectmg.Dtos.VendaReferenciaDTO;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Exceptions.BusinessException;
import com.projectmg.Models.Material;
import com.projectmg.Models.Referencia;
import com.projectmg.Models.HistoricoMaterial;
import com.projectmg.Repositories.HistoricoMaterialRepository;
import com.projectmg.Repositories.MaterialRepository;
import com.projectmg.Repositories.ReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private HistoricoMaterialRepository historicoRepository;

    @Autowired
    private ReferenciaRepository referenciaRepository;

    public List<MaterialDTO> listarTodos() {
        return materialRepository.findAll().stream()
                .map(this::toDTOComUltimoComentario)
                .toList();
    }

    public List<MaterialDTO> listarAtivos() {
        return materialRepository.findAllAtivos().stream()
                .map(this::toDTOComUltimoComentario)
                .toList();
    }

    public List<MaterialDTO> listarUltimos() {
        return materialRepository.findTop5ByOrderByDataAtualizacaoDesc()
                .stream()
                .map(this::toDTOComUltimoComentario)
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
                m.isAtivo(),
                m.getDataProgramadaCompra(),
                m.isFavorito()
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

        // cálculo automático
        calcularTotal(material);

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

        calcularTotal(material);

        material.setAcao(TipoAcao.ATUALIZADO);
        material.setUsuarioUltimaAlteracao(usuario);
        material.setDataAtualizacao(LocalDateTime.now());

        Material salvo = materialRepository.save(material);

        String comentario = dto.ultimoComentario() != null ? dto.ultimoComentario() : "Atualização manual";
        registrarHistorico(salvo, TipoAcao.ATUALIZADO, dto.quantidade(), usuario, comentario);

        return toDTOComUltimoComentario(salvo);
    }

    public void deletarMaterial(Long id, String usuario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        material.setQuantidade(0.0);
        material.setTotalDePecas(0.0);
        material.setAtivo(false);

        materialRepository.save(material);

        registrarHistorico(material, TipoAcao.DELETADO, 0.0, usuario, "Material deletado");
    }

    public MaterialDTO atualizarQuantidade(Long id, Double quantidade, TipoAcao acao, String usuario, String comentario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        if (acao == TipoAcao.ADICIONADO) {
            material.setQuantidade(material.getQuantidade() + quantidade);
        } else if (acao == TipoAcao.RETIRADO) {
            material.setQuantidade(material.getQuantidade() - quantidade);
        }

        calcularTotal(material);

        // reset se ultrapassar limite
        if (material.getLimiteMinimo() != null &&
                material.getTotalDePecas() >= material.getLimiteMinimo()) {
            material.setDataProgramadaCompra(null);
        }

        material.setAcao(acao);
        material.setUsuarioUltimaAlteracao(usuario);
        material.setDataAtualizacao(LocalDateTime.now());

        Material salvo = materialRepository.save(material);

        registrarHistorico(salvo, acao, quantidade, usuario, comentario);

        return toDTOComUltimoComentario(salvo);
    }

    public MaterialDTO retirarPorReferencia(VendaReferenciaDTO dto, String usuario) {

        Material material = materialRepository.findById(dto.materialId())
                .orElseThrow(() -> new BusinessException("Material não encontrado"));

        if (dto.rendimentoReferencia() == null || dto.rendimentoReferencia() <= 0) {
            throw new BusinessException("O rendimento da referência é inválido.");
        }

        if (dto.quantidadePecas() == null || dto.quantidadePecas() <= 0) {
            throw new BusinessException("A quantidade de peças deve ser maior que zero.");
        }

        // cálculo baseado na referência
        Double kgASerRetirado = dto.quantidadePecas() / dto.rendimentoReferencia();

        if (kgASerRetirado > material.getQuantidade()) {
            throw new BusinessException("Estoque insuficiente para realizar a venda.");
        }

        // Remove em KG
        material.setQuantidade(material.getQuantidade() - kgASerRetirado);

        // Recalcula total
        calcularTotal(material);

        material.setAcao(TipoAcao.RETIRADO);
        material.setUsuarioUltimaAlteracao(usuario);
        material.setDataAtualizacao(LocalDateTime.now());

        Material salvo = materialRepository.save(material);

        // Registrar histórico com referência
        HistoricoMaterial historico = new HistoricoMaterial();
        historico.setMaterial(salvo);
        historico.setAcao(TipoAcao.RETIRADO);
        historico.setQuantidadeAlterada(kgASerRetirado);
        historico.setUsuarioUltimaAtualizacao(usuario);
        historico.setComentario(dto.comentario() != null ? dto.comentario() : "Retirada por referência");

        historico.setReferenciaId(dto.referenciaId());
        historico.setQuantidadePecasVendidas(dto.quantidadePecas());

        historicoRepository.save(historico);

        return toDTOComUltimoComentario(salvo);
    }


    private void calcularTotal(Material material) {
        if (material.getRendimento() != null && material.getQuantidade() != null) {
            material.setTotalDePecas(material.getRendimento() * material.getQuantidade());
        }
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

        return toDTOComUltimoComentario(atualizado);
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

        registrarHistorico(salvo, TipoAcao.ATUALIZADO, 0.0, usuario, "Material voltando ao estoque");

        return toDTOComUltimoComentario(salvo);
    }

    public MaterialDTO programarCompra(Long id, String data, String usuario) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        material.setDataProgramadaCompra(java.time.LocalDate.parse(data));
        material.setDataAtualizacao(LocalDateTime.now());
        material.setUsuarioUltimaAlteracao(usuario);

        Material salvo = materialRepository.save(material);

        return toDTOComUltimoComentario(salvo);
    }

    public MaterialDTO alternarFavorito(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        material.setFavorito(!material.isFavorito());
        material.setDataAtualizacao(LocalDateTime.now());

        Material salvo = materialRepository.save(material);

        return toDTOComUltimoComentario(salvo);
    }
}

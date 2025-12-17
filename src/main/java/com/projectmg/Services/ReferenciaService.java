package com.projectmg.Services;

import com.projectmg.Configuration.UsuarioAuditoria;
import com.projectmg.Dtos.ReferenciaDTO;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Exceptions.BusinessException;
import com.projectmg.Models.Referencia;
import com.projectmg.Repositories.ReferenciaRepository;
import com.projectmg.Specs.ReferenciaSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferenciaService {

    private static final String MSG_REFERENCIA = "Referência não encontrada";

    @Autowired
    private ReferenciaRepository referenciaRepository;

    @Autowired
    private ReferenciaSpec referenciaSpec;

    /* =========================
       CONVERSORES
       ========================= */

    public ReferenciaDTO converterReferenciaParaReferenciaDTO(
            Referencia referencia
    ) {
        return new ReferenciaDTO(
                referencia.getId(),
                referencia.getNome(),
                referencia.getReferencia(),
                referencia.getRendimento(),
                referencia.getDataAtualizacao(),
                referencia.getUsuarioUltimaAlteracao(),
                referencia.getAcao(),
                referencia.getAtivo()
        );
    }

    public Referencia converterReferenciaDTOParaReferencia(
            ReferenciaDTO dto
    ) {
        Referencia referencia = new Referencia();
        referencia.setId(dto.id());
        referencia.setNome(dto.nome());
        referencia.setReferencia(dto.referencia());
        referencia.setRendimento(dto.rendimento());
        referencia.setDataAtualizacao(dto.dataAtualizacao());
        referencia.setUsuarioUltimaAlteracao(dto.usuarioUltimaAlteracao());
        referencia.setAcao(dto.acao());
        referencia.setAtivo(dto.ativo());
        return referencia;
    }

    /* =========================
       CRUD
       ========================= */

    public ReferenciaDTO cadastrarReferencia(ReferenciaDTO dto) {

        referenciaSpec.verifyReferenciaNome(dto.nome());
        referenciaSpec.verifyReferenciaRef(dto.referencia());

        referenciaSpec.verifyReferenciaNomeExists(
                referenciaRepository.findByNome(dto.nome())
        );

        referenciaSpec.verifyReferenciaRefExists(
                referenciaRepository.findByReferencia(dto.referencia())
        );

        Referencia referencia = converterReferenciaDTOParaReferencia(dto);

        referencia.setAtivo(true);
        referencia.setAcao(TipoAcao.CRIADO);
        referencia.setDataAtualizacao(LocalDate.now());
        referencia.setUsuarioUltimaAlteracao(
                UsuarioAuditoria.getUsuarioLogado()
        );

        referencia = referenciaRepository.save(referencia);
        return converterReferenciaParaReferenciaDTO(referencia);
    }

    public ReferenciaDTO atualizarReferencia(ReferenciaDTO dto) {

        referenciaSpec.verifyReferenciaId(dto.id());

        Referencia referencia = referenciaRepository
                .findById(dto.id())
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));

        referenciaSpec.verifyReferenciaNome(dto.nome());
        referenciaSpec.verifyReferenciaRef(dto.referencia());
        referenciaSpec.verifyReferenciaNomeDup(dto.nome(), dto.id());
        referenciaSpec.verifyReferenciaRefDup(dto.referencia(), dto.id());

        referencia.setNome(dto.nome());
        referencia.setReferencia(dto.referencia());
        referencia.setRendimento(dto.rendimento());
        referencia.setDataAtualizacao(LocalDate.now());
        referencia.setUsuarioUltimaAlteracao(
                UsuarioAuditoria.getUsuarioLogado()
        );
        referencia.setAcao(TipoAcao.ATUALIZADO);

        referenciaRepository.save(referencia);
        return converterReferenciaParaReferenciaDTO(referencia);
    }

    /**
     * ❌ Exclusão lógica
     */
    public void deletarReferencia(Long id) {
        Referencia referencia = referenciaRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));

        referencia.setAtivo(false);
        referencia.setAcao(TipoAcao.DELETADO);
        referencia.setDataAtualizacao(LocalDate.now());
        referencia.setUsuarioUltimaAlteracao(
                UsuarioAuditoria.getUsuarioLogado()
        );

        referenciaRepository.save(referencia);
    }

    /**
     * ♻ Reativação
     */
    public void reativarReferencia(Long id) {
        Referencia referencia = referenciaRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));

        referencia.setAtivo(true);
        referencia.setAcao(TipoAcao.ATUALIZADO);
        referencia.setDataAtualizacao(LocalDate.now());
        referencia.setUsuarioUltimaAlteracao(
                UsuarioAuditoria.getUsuarioLogado()
        );

        referenciaRepository.save(referencia);
    }

    /* =========================
       CONSULTAS
       ========================= */

    public ReferenciaDTO buscarReferenciaPorId(Long id) {
        return referenciaRepository.findById(id)
                .map(this::converterReferenciaParaReferenciaDTO)
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));
    }

    public List<ReferenciaDTO> buscarReferenciaPorNome(String nome) {
        List<Referencia> refs = referenciaRepository.findByNome(nome);
        referenciaSpec.verifyReferencia(refs);
        return refs.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .collect(Collectors.toList());
    }

    public List<ReferenciaDTO> buscarReferenciaPorReferencia(String referencia) {
        List<Referencia> refs =
                referenciaRepository.findByReferencia(referencia);
        referenciaSpec.verifyReferencia(refs);
        return refs.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .collect(Collectors.toList());
    }

    /**
     * 🔍 Ativos ou todos
     */
    public List<ReferenciaDTO> buscarReferencias(boolean somenteAtivos) {
        List<Referencia> referencias = somenteAtivos
                ? referenciaRepository.findByAtivoTrue()
                : referenciaRepository.findAll();

        return referencias.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .toList();
    }

    /**
     * 🕒 Últimas alterações (ativos + inativos)
     */
    public List<ReferenciaDTO> buscarUltimosAtualizados() {
        return referenciaRepository
                .findTop5ByOrderByDataAtualizacaoDesc()
                .stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .toList();
    }
}

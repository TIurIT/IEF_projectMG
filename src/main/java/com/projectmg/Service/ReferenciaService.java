package com.projectmg.Service;

import com.projectmg.Configuration.Audit.UsuarioAuditoria;
import com.projectmg.Domain.Dto.ReferenciaDTO;
import com.projectmg.Domain.Enum.TipoAcao;
import com.projectmg.Exception.BusinessException;
import com.projectmg.Domain.Entity.Referencia;
import com.projectmg.Repository.ReferenciaRepository;
import com.projectmg.Domain.Validation.ReferenciaSpec;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReferenciaService {

    private static final String MSG_REFERENCIA = "Referência não encontrada";

    private final ReferenciaRepository referenciaRepository;
    private final ReferenciaSpec referenciaSpec;
    private final UsuarioAuditoria usuarioAuditoria;

    public ReferenciaService(
            ReferenciaRepository referenciaRepository,
            ReferenciaSpec referenciaSpec,
            UsuarioAuditoria usuarioAuditoria
    ) {
        this.referenciaRepository = referenciaRepository;
        this.referenciaSpec = referenciaSpec;
        this.usuarioAuditoria = usuarioAuditoria;
    }

    public ReferenciaDTO converterReferenciaParaReferenciaDTO(Referencia referencia) {
        return new ReferenciaDTO(
                referencia.getId(),
                referencia.getNome(),
                referencia.getReferencia(),
                referencia.getRendimento(),
                referencia.getAtivo(),
                referencia.getAcao(),
                referencia.getUsuarioUltimaAlteracao(),
                referencia.getDataAtualizacao()
        );
    }

    public Referencia converterReferenciaDTOParaReferencia(ReferenciaDTO dto) {
        Referencia referencia = new Referencia();
        referencia.setId(dto.id());
        referencia.setNome(dto.nome());
        referencia.setReferencia(dto.referencia());
        referencia.setRendimento(dto.rendimento());
        referencia.setAtivo(dto.ativo());
        referencia.setAcao(dto.acao());
        referencia.setUsuarioUltimaAlteracao(dto.usuarioUltimaAlteracao());
        referencia.setDataAtualizacao(dto.dataAtualizacao());
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
        referencia.setDataAtualizacao(LocalDateTime.now());
        referencia.setUsuarioUltimaAlteracao(
                usuarioAuditoria.getNomeUsuarioLogado()
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
        referencia.setDataAtualizacao(LocalDateTime.now());
        referencia.setUsuarioUltimaAlteracao(
                usuarioAuditoria.getNomeUsuarioLogado()
        );
        referencia.setAcao(TipoAcao.ATUALIZADO);

        referenciaRepository.save(referencia);
        return converterReferenciaParaReferenciaDTO(referencia);
    }

    public void deletarReferencia(Long id) {
        Referencia referencia = referenciaRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));

        referencia.setAtivo(false);
        referencia.setAcao(TipoAcao.DELETADO);
        referencia.setDataAtualizacao(LocalDateTime.now());
        referencia.setUsuarioUltimaAlteracao(
                usuarioAuditoria.getNomeUsuarioLogado()
        );

        referenciaRepository.save(referencia);
    }

    public void reativarReferencia(Long id) {
        Referencia referencia = referenciaRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));

        referencia.setAtivo(true);
        referencia.setAcao(TipoAcao.ATUALIZADO);
        referencia.setDataAtualizacao(LocalDateTime.now());
        referencia.setUsuarioUltimaAlteracao(
                usuarioAuditoria.getNomeUsuarioLogado()
        );

        referenciaRepository.save(referencia);
    }

    public ReferenciaDTO buscarReferenciaPorId(Long id) {
        return referenciaRepository.findById(id)
                .map(this::converterReferenciaParaReferenciaDTO)
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));
    }

    public List<ReferenciaDTO> buscarReferenciaPorNome(String nome) {
        var refs = referenciaRepository.findByNome(nome);
        referenciaSpec.verifyReferencia(refs);
        return refs.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .toList();
    }

    public List<ReferenciaDTO> buscarReferenciaPorReferencia(String referencia) {
        var refs = referenciaRepository.findByReferencia(referencia);
        referenciaSpec.verifyReferencia(refs);
        return refs.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .toList();
    }

    public List<ReferenciaDTO> buscarReferencias(boolean somenteAtivos) {
        var referencias = somenteAtivos
                ? referenciaRepository.findByAtivoTrue()
                : referenciaRepository.findAll();

        return referencias.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .toList();
    }

    public List<ReferenciaDTO> buscarUltimosAtualizados() {
        return referenciaRepository
                .findTop5ByOrderByDataAtualizacaoDesc()
                .stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .toList();
    }
}

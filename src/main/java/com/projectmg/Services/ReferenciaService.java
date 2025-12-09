package com.projectmg.Services;

import com.projectmg.Dtos.ReferenciaDTO;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.Referencia;
import com.projectmg.Repositories.ReferenciaRepository;
import com.projectmg.Configuration.UsuarioAuditoria;
import com.projectmg.Specs.ReferenciaSpec;
import com.projectmg.Exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferenciaService {
    private static final String MSG_REFERENCIA = "Referencia não encontrado";

    @Autowired
    private ReferenciaRepository referenciaRepository;

    @Autowired
    private ReferenciaSpec referenciaSpec;

    public ReferenciaDTO converterReferenciaParaReferenciaDTO(Referencia referencia) {
        return new ReferenciaDTO(
                referencia.getId(),
                referencia.getNome(),
                referencia.getReferencia(),
                referencia.getRendimento(),
                referencia.getDataAtualizacao(),
                referencia.getUsuarioUltimaAlteracao(),
                referencia.getAcao()
        );
    }

    public Referencia converterReferenciaDTOParaReferencia(ReferenciaDTO referenciaDTO) {
        Referencia referencia = new Referencia();
        referencia.setId(referenciaDTO.id());
        referencia.setNome(referenciaDTO.nome());
        referencia.setReferencia(referenciaDTO.referencia());
        referencia.setRendimento(referenciaDTO.rendimento());
        referencia.setDataAtualizacao(referenciaDTO.dataAtualizacao());
        referencia.setUsuarioUltimaAlteracao(referenciaDTO.usuarioUltimaAlteracao());
        referencia.setAcao(referenciaDTO.acao());
        return referencia;
    }

    public ReferenciaDTO cadastrarReferencia(ReferenciaDTO referenciaDTO) {
        referenciaSpec.verifyReferenciaNome(referenciaDTO.nome());
        referenciaSpec.verifyReferenciaRef(referenciaDTO.referencia());

        List<Referencia> referenciaNome = referenciaRepository.findByNome(referenciaDTO.nome());
        List<Referencia> referenciaRef = referenciaRepository.findByReferencia(referenciaDTO.referencia());

        referenciaSpec.verifyReferenciaNomeExists(referenciaNome);
        referenciaSpec.verifyReferenciaRefExists(referenciaRef);

        Referencia referencia = converterReferenciaDTOParaReferencia(referenciaDTO);
        referencia.setAcao(referencia.getId() == null ? TipoAcao.CRIADO : TipoAcao.ATUALIZADO);
        referencia = referenciaRepository.save(referencia);

        return converterReferenciaParaReferenciaDTO(referencia);
    }

    public ReferenciaDTO atualizarReferencia(ReferenciaDTO referenciaDTO) {
        referenciaSpec.verifyReferenciaId(referenciaDTO.id());

        Referencia referenciaExistente = referenciaRepository.findById(referenciaDTO.id())
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));

        referenciaSpec.verifyReferenciaNome(referenciaDTO.nome());
        referenciaSpec.verifyReferenciaRef(referenciaDTO.referencia());
        referenciaSpec.verifyReferenciaNomeDup(referenciaDTO.nome(), referenciaDTO.id());
        referenciaSpec.verifyReferenciaRefDup(referenciaDTO.referencia(), referenciaDTO.id());

        referenciaExistente.setNome(referenciaDTO.nome());
        referenciaExistente.setReferencia(referenciaDTO.referencia());
        referenciaExistente.setDataAtualizacao(LocalDate.now());
        referenciaExistente.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        referenciaExistente.setAcao(TipoAcao.ATUALIZADO);

        referenciaRepository.save(referenciaExistente);
        return converterReferenciaParaReferenciaDTO(referenciaExistente);
    }

    public void deletarReferencia(Long id) {
        Referencia referencia = referenciaRepository.findById(id).orElseThrow();
        referencia.setAcao(TipoAcao.DELETADO);
        referencia.setDataAtualizacao(LocalDate.now());
        referencia.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        referenciaRepository.save(referencia);
        referenciaRepository.deleteById(id);
    }

    public ReferenciaDTO buscarReferenciaPorId(Long id) {
        Referencia referencia = referenciaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_REFERENCIA));
        return converterReferenciaParaReferenciaDTO(referencia);
    }

    public List<ReferenciaDTO> buscarReferenciaPorNome(String nome) {
        List<Referencia> referencias = referenciaRepository.findByNome(nome);
        referenciaSpec.verifyReferencia(referencias);
        return referencias.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .collect(Collectors.toList());
    }

    public List<ReferenciaDTO> buscarReferenciaPorReferencia(String referencia) {
        List<Referencia> referencias = referenciaRepository.findByReferencia(referencia);
        referenciaSpec.verifyReferencia(referencias);
        return referencias.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .collect(Collectors.toList());
    }

    public List<ReferenciaDTO> buscarReferenciaTodos() {
        List<Referencia> referencias = referenciaRepository.findAllAtivos();
        referenciaSpec.verifyReferencia(referencias);
        return referencias.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .collect(Collectors.toList());
    }

    public List<ReferenciaDTO> buscarUltimosAtualizados() {
        List<Referencia> referencias = referenciaRepository.findTop5ByOrderByDataAtualizacaoDesc();
        return referencias.stream()
                .map(this::converterReferenciaParaReferenciaDTO)
                .collect(Collectors.toList());
    }

}
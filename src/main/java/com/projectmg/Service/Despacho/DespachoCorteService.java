package com.projectmg.Service.Despacho;

import com.projectmg.Domain.Dto.Despacho.DespachoCorteDTO;
import com.projectmg.Domain.Dto.Despacho.DespachoCorteRetornoDTO;
import com.projectmg.Domain.Dto.Despacho.HistoricoDespachoDTO;
import com.projectmg.Domain.Entity.Despacho.DespachoCorte;
import com.projectmg.Domain.Entity.Despacho.DespachoCorteRetorno;
import com.projectmg.Domain.Entity.Ordem.OrdemCorte;
import com.projectmg.Domain.Entity.Ordem.OrdemCorteItem;
import com.projectmg.Domain.Enum.TipoServico;
import com.projectmg.Repository.Despacho.DespachoCorteRepository;
import com.projectmg.Repository.Despacho.DespachoCorteRetornoRepository;
import com.projectmg.Repository.Material.MaterialRepository;
import com.projectmg.Repository.ReferenciaRepository;
import com.projectmg.Repository.TerceiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DespachoCorteService {

    @Autowired
    private DespachoCorteRepository despachoRepository;
    @Autowired
    private DespachoCorteRetornoRepository retornoRepository;
    @Autowired
    private TerceiroRepository terceiroRepository;
    @Autowired
    private ReferenciaRepository referenciaRepository;
    @Autowired
    private HistoricoDespachoService historicoService;
    @Autowired
    private MaterialRepository materialRepository;

    public List<DespachoCorte> listarTodos() {
        return despachoRepository.findAll();
    }

    public List<DespachoCorte> filtrar(Long terceiroId, Boolean pendente, Boolean divergencia) {

        List<DespachoCorte> despachos = despachoRepository.findAll();

        if (terceiroId != null) {
            despachos = despachos.stream()
                    .filter(d -> d.getTerceiro().getId().equals(terceiroId))
                    .toList();
        }

        if (pendente != null && pendente) {
            despachos = despachos.stream()
                    .filter(d -> d.getRetorno() == null)
                    .toList();
        }

        if (divergencia != null && divergencia) {
            despachos = despachos.stream()
                    .filter(DespachoCorte::isPossuiDivergencia)
                    .toList();
        }

        return despachos;
    }


    @Transactional
    public DespachoCorte criarDespacho(
            DespachoCorteDTO dto,
            String usuarioLogado
    ) {

        var terceiro = terceiroRepository.findById(dto.terceiroId())
                .orElseThrow(() -> new RuntimeException("Terceiro não encontrado"));

        var referencia = referenciaRepository.findById(dto.referenciaId())
                .orElseThrow(() -> new RuntimeException("Referência não encontrada"));

        var material = materialRepository.findById(dto.materialId())
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));


        DespachoCorte despacho = new DespachoCorte();
        despacho.setTerceiro(terceiro);
        despacho.setReferencia(referencia);
        despacho.setMaterial(material);
        despacho.setQuantidadePecasSolicitadas(dto.quantidadePecasSolicitadas());
        despacho.setQuantidadeMaterialEnviado(dto.quantidadeMaterialEnviado());
        despacho.setTipoServico(TipoServico.CORTE);
        despacho.setUsuarioResponsavel(usuarioLogado);
        despacho.setDataDespacho(LocalDateTime.now());
        despacho.setPossuiDivergencia(false);
        despacho.setDivergenciaResolvida(false);

        despacho = despachoRepository.save(despacho);

        historicoService.registrar(
                new HistoricoDespachoDTO(
                        despacho.getId(),
                        TipoServico.CORTE,
                        terceiro.getId(),
                        "DESPACHO DE CORTE REGISTRADO"
                ),
                usuarioLogado
        );

        return despacho;
    }

    @Transactional
    public DespachoCorteRetorno registrarRetorno(
            Long despachoId,
            DespachoCorteRetornoDTO dto,
            String usuario
    ) {

        var despacho = despachoRepository.findById(despachoId)
                .orElseThrow(() -> new RuntimeException("Despacho de corte não encontrado"));

        if (despacho.getRetorno() != null) {
            throw new RuntimeException("Este despacho já possui retorno registrado");
        }

        Double enviado = despacho.getQuantidadeMaterialEnviado();
        Double retornado = dto.quantidadeMaterialRetornado();
        Double divergencia = enviado - retornado;

        DespachoCorteRetorno retorno = new DespachoCorteRetorno();
        retorno.setDespacho(despacho);
        retorno.setQuantidadeMaterialRetornado(retornado);
        retorno.setDivergenciaMaterial(divergencia);
        retorno.setDataRetorno(LocalDateTime.now());
        retorno.setUsuario(usuario);

        retorno = retornoRepository.save(retorno);

        despacho.setRetorno(retorno);

        if (divergencia != 0) {
            despacho.setPossuiDivergencia(true);
            despacho.setDivergenciaResolvida(false);
        }

        despachoRepository.save(despacho);

        historicoService.registrar(
                new HistoricoDespachoDTO(
                        despacho.getId(),
                        TipoServico.CORTE,
                        despacho.getTerceiro().getId(),
                        divergencia != 0
                                ? "RETORNO DE CORTE REGISTRADO COM DIVERGÊNCIA"
                                : "RETORNO DE CORTE REGISTRADO SEM DIVERGÊNCIA"
                ),
                usuario
        );

        return retorno;
    }

    @Transactional
    public void resolverDivergencia(
            Long despachoId,
            String usuario
    ) {

        var despacho = despachoRepository.findById(despachoId)
                .orElseThrow(() -> new RuntimeException("Despacho não encontrado"));

        if (!Boolean.TRUE.equals(despacho.isPossuiDivergencia())) {
            throw new RuntimeException("Este despacho não possui divergência");
        }

        despacho.setDivergenciaResolvida(true);
        despachoRepository.save(despacho);

        historicoService.registrar(
                new HistoricoDespachoDTO(
                        despacho.getId(),
                        TipoServico.CORTE,
                        despacho.getTerceiro().getId(),
                        "DIVERGÊNCIA DE CORTE RESOLVIDA MANUALMENTE"
                ),
                usuario
        );
    }

    public void converterParaDespacho(
            OrdemCorte ordem,
            String usuario
    ) {
        for (OrdemCorteItem item : ordem.getItens()) {

            DespachoCorte despacho = new DespachoCorte();
            despacho.setReferencia(item.getReferencia());
            despacho.setMaterial(item.getMaterial());
            despacho.setQuantidadePecasSolicitadas(item.getTotalPecas());
            despacho.setUsuarioCricao(usuario);

            // ⚠️ NÃO define bruto
            // ⚠️ NÃO define envio
            // ⚠️ NÃO define retorno

            despachoRepository.save(despacho);
        }

        ordem.setConvertidaParaDespacho(true);
    }

}

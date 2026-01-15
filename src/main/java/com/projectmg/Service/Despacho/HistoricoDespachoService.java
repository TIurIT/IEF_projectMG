package com.projectmg.Service.Despacho;


import com.projectmg.Domain.Dto.Despacho.HistoricoDespachoDTO;
import com.projectmg.Domain.Entity.Despacho.HistoricoDespacho;
import com.projectmg.Repository.Despacho.HistoricoDespachoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class HistoricoDespachoService {

    private final HistoricoDespachoRepository repository;

    public HistoricoDespachoService(HistoricoDespachoRepository repository) {
        this.repository = repository;
    }

    public void registrar(HistoricoDespachoDTO dto, String usuario) {

        HistoricoDespacho h = new HistoricoDespacho();
        h.setDespachoId(dto.despachoId());
        h.setTipoServico(dto.tipoServico());
        h.setTerceiroId(dto.terceiroId());
        h.setAcao(dto.acao());
        h.setUsuario(usuario);
        h.setData(LocalDateTime.now());

        repository.save(h);
    }
}


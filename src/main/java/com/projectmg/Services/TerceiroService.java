package com.projectmg.Services;

import com.projectmg.Dtos.TerceiroDTO;
import com.projectmg.Models.Terceiro;
import com.projectmg.Repositories.TerceiroRepository;
import com.projectmg.Specs.TerceiroSpec;
import com.projectmg.Exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerceiroService {

    private static final String MSG_TERCEIRO = "Terceiro não encontrado";

    @Autowired
    private TerceiroRepository terceiroRepository;
    @Autowired
    private TerceiroSpec terceiroSpec;

    public Terceiro converterTerceiroDTOParaTerceiro(TerceiroDTO TerceiroDTO){
        Terceiro terceiro = new Terceiro();
        terceiro.setId(TerceiroDTO.id());
        terceiro.setNome(TerceiroDTO.nome());
        terceiro.setBairro(TerceiroDTO.bairro());
        terceiro.setTelefone(TerceiroDTO.telefone());
        terceiro.setCnpj(TerceiroDTO.cnpj());
        terceiro.setTipoServico(TerceiroDTO.tipoServico());
        return terceiro;
    }

    public TerceiroDTO converterTerceiroParaTerceiroDTO(Terceiro terceiro){
        return new TerceiroDTO(
        terceiro.getId(),
        terceiro.getNome(),
        terceiro.getBairro(),
        terceiro.getTelefone(),
        terceiro.getCnpj(),
        terceiro.getTipoServico()
        );
    }

    public TerceiroDTO cadastrarTerceiro(TerceiroDTO terceiroDTO){
        terceiroSpec.verifyTerceiroNome(terceiroDTO.nome());
        terceiroSpec.verifyTerceiroBairro(terceiroDTO.bairro());
        terceiroSpec.verifyTerceiroTelefone(terceiroDTO.telefone());
        List<Terceiro> terceiroNome = terceiroRepository.findByNome(terceiroDTO.nome());
        terceiroSpec.verifyTerceiroNomeExists(terceiroNome);
        terceiroSpec.verifyTerceiroBairroExists(terceiroNome);
        Terceiro terceiro = converterTerceiroDTOParaTerceiro(terceiroDTO);
        terceiro = terceiroRepository.save(terceiro);
        return converterTerceiroParaTerceiroDTO(terceiro);
    }

    public TerceiroDTO atualizarTerceiro(TerceiroDTO terceiroDTO){
        List<Terceiro> terceiroNome = terceiroRepository.findByNome(terceiroDTO.nome());
        terceiroSpec.verifyTerceiroNomeExists(terceiroNome);
        terceiroSpec.verifyTerceiroBairroExists(terceiroNome);
        terceiroSpec.verifyTerceiroId(terceiroDTO.id());

        Terceiro terceiro = terceiroRepository.findById(terceiroDTO.id())
                .orElseThrow(() -> new BusinessException(MSG_TERCEIRO));
        terceiro = converterTerceiroDTOParaTerceiro(terceiroDTO);
        terceiroRepository.save(terceiro);
        return converterTerceiroParaTerceiroDTO(terceiro);
    }

    public void deletarTerceiro(Long id){
        terceiroRepository.deleteById(id);
    }

    public TerceiroDTO buscarTerceiroPorId(Long id){
        Terceiro terceiro = terceiroRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_TERCEIRO));
        return converterTerceiroParaTerceiroDTO(terceiro);
    }

    public List<TerceiroDTO> buscarTerceiroPorNome(String nome){
        List<Terceiro> terceiros = terceiroRepository.findByNome(nome);
        terceiroSpec.verifyTerceiro(terceiros);
        List<TerceiroDTO> dtos = new ArrayList<>();
        terceiros.forEach(terceiro -> {
           dtos.add(converterTerceiroParaTerceiroDTO(terceiro));
        });

        return dtos;
    }

    public List<TerceiroDTO> buscarTerceiroPorBairro(String bairro){
        List<Terceiro> terceiros = terceiroRepository.findByBairro(bairro);
        terceiroSpec.verifyTerceiro(terceiros);
        List<TerceiroDTO> dtos = new ArrayList<>();
        terceiros.forEach(terceiro -> {
           dtos.add(converterTerceiroParaTerceiroDTO(terceiro));
        });

        return dtos;
    }

    public List<TerceiroDTO> buscarTerceiros(){
        List<Terceiro> terceiros = terceiroRepository.findAll();
        terceiroSpec.verifyTerceiro(terceiros);
        List<TerceiroDTO> dtos = new ArrayList<>();
        terceiros.forEach(terceiro -> {
            dtos.add(converterTerceiroParaTerceiroDTO(terceiro));
        });

        return dtos;
    }
}

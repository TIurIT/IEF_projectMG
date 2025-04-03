package com.projectmg.Services;

import com.projectmg.Dto.OrdemProducaoDTO;
import com.projectmg.Models.OrdemProducao;
import com.projectmg.Repositories.OrdemProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemProducaoService {

    private static final String MSG_OPERACAO = "Operação nao encontrada";

   @Autowired
   private OrdemProducaoRepository ordemProducaoRepository;

   public OrdemProducaoDTO converterOrdemProducaoParaOrdemProducaoDTO(OrdemProducao ordemProducao){
      OrdemProducaoDTO ordemProducaoDTO = new OrdemProducaoDTO();
      ordemProducaoDTO.setId(ordemProducao.getId());
      ordemProducaoDTO.setDataDeCriacao(ordemProducao.getDataDeCriacao());
      ordemProducaoDTO.setCliente(ordemProducao.getCliente());
      ordemProducaoDTO.setItens(ordemProducao.getItens());
      return ordemProducaoDTO;
   }

   public OrdemProducao converterOrdemProducaoDTOParaOrdemProducao(OrdemProducaoDTO ordemProducaoDTO){
      OrdemProducao ordemProducao = new OrdemProducao();
      ordemProducao.setId(ordemProducaoDTO.getId());
      ordemProducao.setDataDeCriacao(ordemProducaoDTO.getDataDeCriacao());
      ordemProducao.setCliente(ordemProducaoDTO.getCliente());
      ordemProducao.setItens(ordemProducaoDTO.getItens());
      return ordemProducao;
   }

   public OrdemProducaoDTO cadastrarOrdemProducao(OrdemProducaoDTO ordemProducaoDTO){
      OrdemProducao ordemProducao = converterOrdemProducaoDTOParaOrdemProducao(ordemProducaoDTO);
      ordemProducao = ordemProducaoRepository.save(ordemProducao);
      return converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao);
   }

   public OrdemProducaoDTO atualizarOrdemProducao(OrdemProducaoDTO ordemProducaoDTO){
      OrdemProducao ordemProducao = converterOrdemProducaoDTOParaOrdemProducao(ordemProducaoDTO);
      ordemProducao = ordemProducaoRepository.save(ordemProducao);
      return converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao);
   }

   public void deletarOrdemProducao(Long id){
      ordemProducaoRepository.deleteById(id);
   }

   public OrdemProducaoDTO buscarOrdemProducaoPorId(Long id){
      OrdemProducao ordemProducao = ordemProducaoRepository.findById(id)
              .orElseThrow(() -> new RuntimeException(MSG_OPERACAO));
      return converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao);
   }

   public List<OrdemProducaoDTO> buscarOrdemProducaoPorCliente(String nome){
      List<OrdemProducao> ordemProducaos = ordemProducaoRepository.findByClienteByNome(nome);
      List<OrdemProducaoDTO> dtos = new java.util.ArrayList<>();
      ordemProducaos.forEach(ordemProducao -> {
         dtos.add(converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao));
      });
      return dtos;
   }
}

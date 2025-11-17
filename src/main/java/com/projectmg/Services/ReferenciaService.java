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

@Service
public class ReferenciaService {

    private static final String MSG_PRODUTO = "Produto não encontrado";

    @Autowired
    private ReferenciaRepository referenciaRepository;

    @Autowired
    private ReferenciaSpec referenciaSpec;

    public ReferenciaDTO converterProdutoParaProdutoDTO(Referencia referencia){
        ReferenciaDTO referenciaDTO = new ReferenciaDTO();
        referenciaDTO.setId(referencia.getId());
        referenciaDTO.setNome(referencia.getNome());
        referenciaDTO.setReferencia(referencia.getReferencia());
        referenciaDTO.setDataAtualizacao(referencia.getDataAtualizacao());
        referenciaDTO.setUsuarioUltimaAlteracao(referencia.getUsuarioUltimaAlteracao());
        referenciaDTO.setAcao(referencia.getAcao());

        return referenciaDTO;
    }

    public Referencia converterProdutoDTOParaProduto(ReferenciaDTO referenciaDTO){
        Referencia referencia = new Referencia();
        referencia.setId(referenciaDTO.getId());
        referencia.setNome(referenciaDTO.getNome());
        referencia.setReferencia(referenciaDTO.getReferencia());
        referencia.setDataAtualizacao(referenciaDTO.getDataAtualizacao());
        referencia.setUsuarioUltimaAlteracao(referenciaDTO.getUsuarioUltimaAlteracao());
        referencia.setAcao(referenciaDTO.getAcao());

        return referencia;
    }

    public ReferenciaDTO cadastrarProduto(ReferenciaDTO referenciaDTO){
        referenciaSpec.verifyProdutoNome(referenciaDTO.getNome());
        referenciaSpec.verifyProdutoRef(referenciaDTO.getReferencia());
        List<Referencia> referenciaNome = referenciaRepository.findByNome(referenciaDTO.getNome());
        List<Referencia> referenciaRef = referenciaRepository.findByReferencia(referenciaDTO.getReferencia());
        referenciaSpec.verifyProdutoNomeExists(referenciaNome);
        referenciaSpec.verifyProdutoRefExists(referenciaRef);
        Referencia referencia = converterProdutoDTOParaProduto(referenciaDTO);
        referencia.setAcao(referencia.getId() == null ? TipoAcao.CRIADO : TipoAcao.ATUALIZADO);
        referencia = referenciaRepository.save(referencia);

        return converterProdutoParaProdutoDTO(referencia);
    }

    public ReferenciaDTO atualizarProduto(ReferenciaDTO referenciaDTO){
        referenciaSpec.verifyProdutoId(referenciaDTO.getId());
        Referencia referenciaExistente = referenciaRepository.findById(referenciaDTO.getId())
                .orElseThrow(() -> new BusinessException(MSG_PRODUTO));
        referenciaSpec.verifyProdutoNome(referenciaDTO.getNome());
        referenciaSpec.verifyProdutoRef(referenciaDTO.getReferencia());
        referenciaSpec.verifyProdutoNomeDup(referenciaDTO.getNome(), referenciaDTO.getId());
        referenciaSpec.verifyProdutoRefDup(referenciaDTO.getReferencia(), referenciaDTO.getId());
        referenciaExistente.setNome(referenciaDTO.getNome());
        referenciaExistente.setReferencia(referenciaDTO.getReferencia());
        referenciaExistente.setDataAtualizacao(LocalDate.now());
        referenciaExistente.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        referenciaExistente.setAcao(TipoAcao.ATUALIZADO);
        referenciaRepository.save(referenciaExistente);

        return converterProdutoParaProdutoDTO(referenciaExistente);
    }

    public void deletarProduto(Long id){
        Referencia referencia = referenciaRepository.findById(id).orElseThrow();
        referencia.setAcao(TipoAcao.DELETADO);
        referencia.setDataAtualizacao(LocalDate.now());
        referencia.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        referenciaRepository.save(referencia);
        referenciaRepository.deleteById(id);
    }

    public ReferenciaDTO buscarProdutoPorId(Long id){
        Referencia referencia = referenciaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_PRODUTO));
        return converterProdutoParaProdutoDTO(referencia);
    }

    public List<ReferenciaDTO> buscarProdutoPorNome(String nome){
        List<Referencia> referencias = referenciaRepository.findByNome(nome);
        referenciaSpec.verifyProduto(referencias);
        List<ReferenciaDTO> dtos = new java.util.ArrayList<>();
        referencias.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }

    public List<ReferenciaDTO> buscarProdutoPorReferencia(String referencia){
        List<Referencia> referencias = referenciaRepository.findByReferencia(referencia);
        referenciaSpec.verifyProduto(referencias);
        List<ReferenciaDTO> dtos = new java.util.ArrayList<>();
        referencias.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }

    public List<ReferenciaDTO> buscarProdutoTodos(){
        List<Referencia> referencias = referenciaRepository.findAllAtivos();
        referenciaSpec.verifyProduto(referencias);
        List<ReferenciaDTO> dtos = new java.util.ArrayList<>();
        referencias.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }
}

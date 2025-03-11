package com.projectmg.Services;

import com.projectmg.DTO.EstoqueDto;
import com.projectmg.Models.Estoque;
import com.projectmg.Repositories.EstoqueRepository;
import com.projectmg.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EstoqueService {

    private static final String MSG_ESTOQUE = "Produto não encontrado";
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Estoque converterEstoqueDtoParaEstoque(EstoqueDto EstoqueDto){
        Estoque estoque = new Estoque();
        estoque.setId(EstoqueDto.getId());
        estoque.setTipo(EstoqueDto.getTipo());
        estoque.setNome(EstoqueDto.getNome());
        estoque.setMarca(EstoqueDto.getMarca());
        estoque.setQuantidade(EstoqueDto.getQuantidade());
        estoque.setDataDeCriacao(EstoqueDto.getDataDeCriacao());
        return estoque;
    }

    public EstoqueDto converterEstoqueParaEstoqueDto(Estoque estoque){
        EstoqueDto EstoqueDto = new EstoqueDto();
        EstoqueDto.setId(estoque.getId());
        EstoqueDto.setTipo(estoque.getTipo());
        EstoqueDto.setNome(estoque.getNome());
        EstoqueDto.setMarca(estoque.getMarca());
        EstoqueDto.setQuantidade(estoque.getQuantidade());
        EstoqueDto.setDataDeCriacao(estoque.getDataDeCriacao());
        return EstoqueDto;
    }

    public EstoqueDto cadastrarEstoque(EstoqueDto EstoqueDto) {
        Estoque estoque = converterEstoqueDtoParaEstoque(EstoqueDto);
        estoque = estoqueRepository.save(estoque);
        return converterEstoqueParaEstoqueDto(estoque);
    }

    public EstoqueDto atualizarEstoque(EstoqueDto EstoqueDto) {
        Estoque estoque = estoqueRepository.findById(EstoqueDto.getId())
                        .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
        estoque = converterEstoqueDtoParaEstoque(EstoqueDto);
        estoqueRepository.save(estoque);
        return  converterEstoqueParaEstoqueDto(estoque);
    }

    public void excluirEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

    public EstoqueDto buscarEstoque(Long id) {
       Estoque estoque = estoqueRepository.findById(id)
               .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
       return converterEstoqueParaEstoqueDto(estoque);
    }

    public EstoqueDto buscarEstoquePorNome(String nome) {
        Estoque estoque = estoqueRepository.findByNome(nome);
        return converterEstoqueParaEstoqueDto(estoque);
    }

    public EstoqueDto buscarEstoquePorTipo(String tipo) {
        Estoque estoque = estoqueRepository.findByTipo(tipo);
        return converterEstoqueParaEstoqueDto(estoque);
    }

    public EstoqueDto buscarEstoquePorMarca(String marca) {
        Estoque estoque = estoqueRepository.findByMarca(marca);
        return converterEstoqueParaEstoqueDto(estoque);
    }

    public List<Estoque> findAllByNome(String nome) {
        return estoqueRepository.findAllByNome(nome);
    }

    public List<Estoque> findAllByTipo(String tipo) {
        return estoqueRepository.findAllByTipo(tipo);
    }

    public List<Estoque> findAllByMarca(String marca) {
        return estoqueRepository.findAllByMarca(marca);
    }

}

package Services;

import DTO.EstoqueDto;
import Models.Estoque;
import Repositories.EstoqueRepository;
import exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EstoqueService {

    private static final String MSG_ESTOQUE = "Produto não encontrado";
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Estoque converterEstoqueDtoParaEstoque(EstoqueDto estoqueDto){
        Estoque estoque = new Estoque();
        estoque.setId(estoqueDto.getId());
        estoque.setTipo(estoqueDto.getTipo());
        estoque.setNome(estoqueDto.getNome());
        estoque.setMarca(estoqueDto.getMarca());
        estoque.setQuantidade(estoqueDto.getQuantidade());
        estoque.setDataDeCriacao(estoqueDto.getDataDeCriacao());
        return estoque;
    }

    public EstoqueDto converterEstoqueParaEstoqueDto(Estoque estoque){
        EstoqueDto estoqueDto = new EstoqueDto();
        estoqueDto.setId(estoque.getId());
        estoqueDto.setTipo(estoque.getTipo());
        estoqueDto.setNome(estoque.getNome());
        estoqueDto.setMarca(estoque.getMarca());
        estoqueDto.setQuantidade(estoque.getQuantidade());
        estoqueDto.setDataDeCriacao(estoque.getDataDeCriacao());
        return estoqueDto;
    }

    public EstoqueDto cadastrarEstoque(EstoqueDto estoqueDto) {
        Estoque estoque = converterEstoqueDtoParaEstoque(estoqueDto);
        estoque = estoqueRepository.save(estoque);
        return converterEstoqueParaEstoqueDto(estoque);
    }

    public EstoqueDto atualizarEstoque(EstoqueDto estoqueDto) {
        Estoque estoque = estoqueRepository.findById(estoqueDto.getId())
                        .orElseThrow(() -> new BusinessException(MSG_ESTOQUE));
        estoque = converterEstoqueDtoParaEstoque(estoqueDto);
        estoqueRepository.save(estoque);
        return  converterEstoqueParaEstoqueDto(estoque);
    }

    public void excluirEstoque(UUID id) {
        estoqueRepository.deleteById(id);
    }

    public EstoqueDto buscarEstoque(UUID id) {
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
}

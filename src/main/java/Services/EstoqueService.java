package Services;

import Models.Estoque;
import Repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public void cadastrarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    public void atualizarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    public void excluirEstoque(Estoque estoque) {
        estoqueRepository.delete(estoque);
    }

    public Estoque buscarEstoque(UUID id) {
        return estoqueRepository.findById(id);
    }
}

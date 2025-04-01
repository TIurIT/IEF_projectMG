package com.projectmg.Services;

import com.projectmg.Dto.ClienteDTO;
import com.projectmg.Models.Cliente;
import com.projectmg.Repositories.ClienteRepository;
import com.projectmg.Specs.ClienteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private static final String MSG_CLIENTE = "Cliente não encontrado";

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteSpec clienteSpec;

    public ClienteDTO converterClienteParaClienteDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setCpfCnpj(cliente.getCpfCnpj());
        return clienteDTO;
    }

    public Cliente converterClienteDTOParaCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCpfCnpj(clienteDTO.getCpfCnpj());
        return cliente;
    }

    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO){
        clienteSpec.verifyClienteNome(clienteDTO.getNome());
        clienteSpec.verifyClienteEmail(clienteDTO.getEmail());
        clienteSpec.verifyClienteTelefone(clienteDTO.getTelefone());
        clienteSpec.verifyClienteCpfCnpj(clienteDTO.getCpfCnpj());
        Cliente clienteNome = clienteRepository.findByNome(clienteDTO.getNome());
        Cliente clienteEmail = clienteRepository.findByEmail(clienteDTO.getEmail());
        Cliente clienteCpfCnpj = clienteRepository.findByCpfCnpj(clienteDTO.getCpfCnpj());
        clienteSpec.verifyClienteCpfCnpjDup(clienteCpfCnpj);
        clienteSpec.verifyClienteEmailDup(clienteEmail);
        clienteSpec.verifyClienteNomeExist(clienteNome);

        Cliente cliente = converterClienteDTOParaCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return converterClienteParaClienteDTO(cliente);
    }

    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO){
        Cliente clienteNome = clienteRepository.findByNome(clienteDTO.getNome());
        clienteSpec.verifyClienteNomeExist(clienteNome);
        clienteSpec.verifyClienteId(clienteDTO.getId());
        Cliente cliente = converterClienteDTOParaCliente(clienteDTO);
        clienteSpec.verifyCpfCnpClienteEmUso(cliente, clienteDTO);
        clienteSpec.verifyEmailClienteEmUso(cliente, clienteDTO);
        cliente = clienteRepository.save(cliente);
        return converterClienteParaClienteDTO(cliente);
    }

    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_CLIENTE));
        return converterClienteParaClienteDTO(cliente);
    }

    public List<ClienteDTO> buscarClientePorNome(String nome){
        List<Cliente> clientes = clienteRepository.findByNomeAll(nome);
        clienteSpec.verifyCliente(clientes);
        List<ClienteDTO> dtos = new java.util.ArrayList<>();
        clientes.forEach(cliente -> {
            dtos.add(converterClienteParaClienteDTO(cliente));
        });

        return dtos;
    }
}

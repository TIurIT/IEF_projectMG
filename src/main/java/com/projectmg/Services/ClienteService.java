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
        clienteDTO.setCpf_cnpj(cliente.getCpf_Cnpj());
        return clienteDTO;
    }

    public Cliente converterClienteDTOParaCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCpf_Cnpj(clienteDTO.getCpf_cnpj());
        return cliente;
    }

    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO){
        Cliente cliente = converterClienteDTOParaCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return converterClienteParaClienteDTO(cliente);
    }

    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO){
        Cliente cliente = converterClienteDTOParaCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return converterClienteParaClienteDTO(cliente);
    }

    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MSG_CLIENTE"));
        return converterClienteParaClienteDTO(cliente);
    }

    public List<ClienteDTO> buscarClientePorNome(String nome){
        List<Cliente> clientes = clienteRepository.findByNome(nome);
        clienteSpec.verificarCliente(clientes);
        List<ClienteDTO> dtos = new java.util.ArrayList<>();
        clientes.forEach(cliente -> {
            dtos.add(converterClienteParaClienteDTO(cliente));
        });

        return dtos;
    }
}

package com.projectmg.Specs;

import com.projectmg.Dto.ClienteDTO;
import com.projectmg.Models.Cliente;
import com.projectmg.Repositories.ClienteRepository;
import com.projectmg.Services.ClienteService;
import com.projectmg.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class ClienteSpec {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    private static final String MSG_EMAIL = "Usuário já cadastrado com e-mail: %s.";
    private static final String MSG_CLIENTE = "Cliente não encontrado";
    
    
    public void verificarCliente(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            throw new BusinessException("MSG_CLIENTE");
        }
    }
    
    public void verificarClienteNome(List<Cliente> clientes) {
        if (clientes.size() > 0) {
            throw new BusinessException("MSG_CLIENTE");
        }
    }
    
    public void verificarClienteEmail(List<Cliente> clientes) {
        if (nonNull(clientes)) {
            throw new BusinessException("MSG_CLIENTE");
        }
    }
    
    public void verificarClienteTelefone(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            throw new BusinessException("MSG_CLIENTE");
        }
    }
    
    public void verificarClienteCpfCnpj(List<Cliente> clientes) {
        if (isNull(clientes)) {
            throw new BusinessException("MSG_CLIENTE");
        }
    }
    
    public void verificarClienteId(Long id) {
        if (isNull(id)) {
            throw new BusinessException("MSG_CLIENTE");
        }
    }
    
    public void verificarEmailClienteEmUso(Cliente cliente, ClienteDTO clienteDTO) {
        boolean alterouEmail = !(cliente.getEmail().equals(clienteDTO.getEmail()));
        
        if(alterouEmail) {
            boolean existeEmail = nonNull(clienteRepository.findByEmail(clienteDTO.getEmail()));
            if (existeEmail)
                throw new BusinessException(String.format(MSG_EMAIL, clienteDTO.getEmail()));
        }
    }
    
    public void verificarCpfCnpClienteEmUso(Cliente cliente, ClienteDTO clienteDTO) {
        boolean alterouCpfCnpj = !(cliente.getCpf_Cnpj().equals(clienteDTO.getCpf_cnpj()));
        
        if(alterouCpfCnpj) {
            boolean existeCpfCnpj = nonNull(clienteRepository.findByCpfCnpj(clienteDTO.getCpf_cnpj()));
            if (existeCpfCnpj)
                throw new BusinessException(String.format(MSG_EMAIL, clienteDTO.getCpf_cnpj()));
        }
    }
}

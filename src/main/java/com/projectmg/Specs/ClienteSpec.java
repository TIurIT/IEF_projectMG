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
    private static final String MSG_CPF = "Usuário já cadastrado com cpf/cnpj: %s.";
    private static final String MSG_NOME_EXIST = "Usuário já cadastrado com nome: %s.";
    private static final String MSG_CLIENTE = "Cliente não encontrado";
    private static final String MSG_CLIENTE_NOME = "Campo nome deve ser preenchido";
    private static final String MSG_CLIENTE_EMAIL = "Campo email deve ser preenchido";
    private static final String MSG_CLIENTE_TELEFONE = "Campo telefone deve ser preenchido";
    private static final String MSG_CLIENTE_CPF = "Campo cpf/cnpj deve ser preenchido";

    
    
    public void verifyCliente(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            throw new BusinessException(MSG_CLIENTE);
        }
    }

    public void verifyClienteNome(String nome) {
        if (nome.isEmpty()) {
            throw new BusinessException(MSG_CLIENTE_NOME);
        }
    }
    
    public void verifyClienteNomeExist(List<Cliente> clientes) {
        if (clientes.size() > 0) {
            throw new BusinessException(MSG_NOME_EXIST);
        }
    }
    
    public void verifyClienteEmail(List<Cliente> clientes) {
        if (nonNull(clientes)) {
            throw new BusinessException(MSG_CLIENTE_EMAIL);
        }
    }
    
    public void verifyClienteTelefone(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            throw new BusinessException(MSG_CLIENTE_TELEFONE);
        }
    }
    
    public void verifyClienteCpfCnpj(List<Cliente> clientes) {
        if (isNull(clientes)) {
            throw new BusinessException(MSG_CLIENTE_CPF);
        }
    }
    
    public void verifyClienteId(Long id) {
        if (isNull(id)) {
            throw new BusinessException(MSG_CLIENTE);
        }
    }
    
    public void verifyEmailClienteEmUso(Cliente cliente, ClienteDTO clienteDTO) {
        boolean alterouEmail = !(cliente.getEmail().equals(clienteDTO.getEmail()));
        
        if(alterouEmail) {
            boolean existeEmail = nonNull(clienteRepository.findByEmail(clienteDTO.getEmail()));
            if (existeEmail)
                throw new BusinessException(String.format(MSG_EMAIL, clienteDTO.getEmail()));
        }
    }
    
//    public void verifyCpfCnpClienteEmUso(Cliente cliente, ClienteDTO clienteDTO) {
//        boolean alterouCpfCnpj = !(cliente.getCpf_cnpj().equals(clienteDTO.getCpf_cnpj()));
//
//        if(alterouCpfCnpj) {
//            boolean existeCpfCnpj = nonNull(clienteRepository.findByCpfCnpj(clienteDTO.getCpf_cnpj()));
//            if (existeCpfCnpj)
//                throw new BusinessException(String.format(MSG_CPF, clienteDTO.getCpf_cnpj()));
//        }
//    }
}

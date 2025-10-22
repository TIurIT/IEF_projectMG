package com.projectmg.Specs;

import com.projectmg.Dtos.ClienteDTO;
import com.projectmg.Models.Cliente;
import com.projectmg.Repositories.ClienteRepository;
import com.projectmg.Exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class ClienteSpec {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    private static final String MSG_EMAIL = "Email já cadastrado.";
    private static final String MSG_CPF = "CPF/CNPJ já cadastrado.";
    private static final String MSG_NOME_EXIST = "Cliente já cadastrado.";
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
    
    public void verifyClienteNomeExist(Cliente nome) {
        if (nonNull(nome)) {
            throw new BusinessException(MSG_NOME_EXIST);
        }
    }
    
    public void verifyClienteEmail(String email) {
        if (isNull(email)) {
            throw new BusinessException(MSG_CLIENTE_EMAIL);
        }
    }

    public void verifyClienteEmailDup(Cliente email) {
        if (nonNull(email)) {
            throw new BusinessException(MSG_EMAIL);
        }
    }

    public void verifyClienteTelefone(String telefone) {
        if (isNull(telefone)) {
            throw new BusinessException(MSG_CLIENTE_TELEFONE);
        }
    }
    
    public void verifyClienteCpfCnpj(String cpfCnpj) {
        if (isNull(cpfCnpj)) {
            throw new BusinessException(MSG_CLIENTE_CPF);
        }
    }

    public void verifyClienteCpfCnpjDup(Cliente cpfCnpj) {
        if (nonNull(cpfCnpj)) {
            throw new BusinessException(MSG_CPF);
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
                throw new BusinessException((MSG_EMAIL));
        }
    }
    
    public void verifyCpfCnpClienteEmUso(Cliente cliente, ClienteDTO clienteDTO) {
        boolean alterouCpfCnpj = !(cliente.getCpfCnpj().equals(clienteDTO.getCpfCnpj()));

        if(alterouCpfCnpj) {
            boolean existeCpfCnpj = nonNull(clienteRepository.findByCpfCnpj(clienteDTO.getCpfCnpj()));
            if (existeCpfCnpj)
                throw new BusinessException(MSG_CPF);
        }
    }
}

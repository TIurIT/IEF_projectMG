package com.projectmg.Specs;

import com.projectmg.Repositories.UsuarioRepository;
import com.projectmg.Dtos.UsuarioDTO;
import com.projectmg.Models.Usuario;
import com.projectmg.Exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class UsuarioSpec {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String MSG_EMAIL = "Email já cadastrado.";
    private static final String MSG_ID = "Id não pode ser nulo";

    public void verifyEmailDup(Usuario usuario) {
        if (nonNull(usuario))
            throw new BusinessException(MSG_EMAIL);
    }

    public void verifyCampoIdNulo(Long id) {
        if (isNull(id)) throw new BusinessException(MSG_ID);
    }

    public void verifyEmailEmUso(Usuario usuario, UsuarioDTO usuarioDTO) {
        boolean alterouEmail = !(usuario.getEmail().equals(usuarioDTO.getEmail()));

        if(alterouEmail) {
            boolean existeEmail = nonNull(usuarioRepository.findByEmail(usuarioDTO.getEmail()));
            if (existeEmail)
                throw new BusinessException(MSG_EMAIL);
        }
    }
}

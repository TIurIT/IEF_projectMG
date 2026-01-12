package com.projectmg.Configuration.Audit;

import com.projectmg.Domain.Entity.Usuario;
import com.projectmg.Repository.UsuarioRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAuditoria {

    private final UsuarioRepository usuarioRepository;

    public UsuarioAuditoria(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String getEmailUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null ||
                !auth.isAuthenticated() ||
                auth instanceof AnonymousAuthenticationToken) {
            return "Sistema";
        }

        return auth.getName();
    }

    public String getNomeUsuarioLogado() {
        String email = getEmailUsuarioLogado();

        if ("Sistema".equals(email)) {
            return "Sistema";
        }

        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null ? usuario.getNome() : email;
    }
}

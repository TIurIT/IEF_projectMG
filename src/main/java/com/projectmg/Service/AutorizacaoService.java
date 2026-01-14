package com.projectmg.Service;

import com.projectmg.Domain.Enum.TipoAcesso;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AutorizacaoService {

    public boolean podeCriarMaterial() {
        return hasAnyRole(
                TipoAcesso.ADMIN,
                TipoAcesso.GERENTE
        );
    }

    public boolean podeAlterarEstoque() {
        return hasAnyRole(
                TipoAcesso.ADMIN,
                TipoAcesso.GERENTE,
                TipoAcesso.PRODUCAO
        );
    }

    public boolean podeExcluirMaterial() {
        return hasAnyRole(
                TipoAcesso.ADMIN
        );
    }

    public boolean podeVisualizar() {
        return isAuthenticated();
    }

    // 🔒 MÉTODOS AUXILIARES

    private boolean hasAnyRole(TipoAcesso... acessos) {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return auth.getAuthorities().stream()
                .anyMatch(a ->
                        Arrays.stream(acessos)
                                .anyMatch(tipo ->
                                        a.getAuthority()
                                                .equals("ROLE_" + tipo.name())
                                )
                );
    }


    private boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return auth != null && auth.isAuthenticated();
    }
}

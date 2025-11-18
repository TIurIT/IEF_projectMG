package com.projectmg.Services;

import com.projectmg.Dtos.UsuarioDTO;
import com.projectmg.Models.Usuario;
import com.projectmg.Repositories.UsuarioRepository;
import com.projectmg.Specs.ClienteSpec;
import com.projectmg.Specs.UsuarioSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    public static final String MSG_USUARIO = "Usuario não encontrado";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioSpec usuarioSpec;
    @Autowired
    private ClienteSpec clienteSpec;

    public UsuarioDTO converterUsuarioParaUsuarioDto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(null);
        usuarioDTO.setTipo(usuario.getTipoAcesso());
        return usuarioDTO;
    }

    public Usuario converterUsuarioDtoParaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTipoAcesso(usuarioDTO.getTipoAcesso());
        return usuario;
    }

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuarioEmail = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        usuarioSpec.verifyEmailDup(usuarioEmail);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuarioDTO.setSenha(encoder.encode(usuarioDTO.getSenha()));


        Usuario usuario = converterUsuarioDtoParaUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return converterUsuarioParaUsuarioDto(usuario);
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO){
        usuarioSpec.verifyCampoIdNulo(usuarioDTO.getId());
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() -> new RuntimeException(MSG_USUARIO));
        usuarioSpec.verifyEmailEmUso(usuario, usuarioDTO);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (usuarioDTO.getSenha() == null || usuarioDTO.getSenha().isEmpty()) {
            usuarioDTO.setSenha(usuario.getSenha());
        } else {
            usuarioDTO.setSenha(encoder.encode(usuarioDTO.getSenha()));
        }

        usuario = converterUsuarioDtoParaUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return  converterUsuarioParaUsuarioDto(usuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(MSG_USUARIO));
        return converterUsuarioParaUsuarioDto(usuario);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email);
        return converterUsuarioParaUsuarioDto(usuario);
    }
}

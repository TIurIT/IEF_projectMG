package com.projectmg.Services;

import com.projectmg.Dto.UsuarioDTO;
import com.projectmg.Models.Usuario;
import com.projectmg.Repositories.UsuarioRepository;
import com.projectmg.Specs.UsuarioSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    public static final String MSG_USUARIO = "Usuario não encontrado";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioSpec usuarioSpec;

    public UsuarioDTO converterUsuarioParaUsuarioDto(Usuario usuario){
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSenha(usuario.getSenha());
        return usuarioDto;
    }

    public Usuario converterUsuarioDtoParaUsuario(UsuarioDTO usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        return usuario;
    }

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDto){
        Usuario usuarioEmail = usuarioRepository.findByEmail(usuarioDto.getEmail());
        usuarioSpec.verifyEmailDup(usuarioEmail);

        Usuario usuario = converterUsuarioDtoParaUsuario(usuarioDto);
        usuario = usuarioRepository.save(usuario);
        return converterUsuarioParaUsuarioDto(usuario);
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDto){
        usuarioSpec.verifyCampoIdNulo(usuarioDto.getId());
        Usuario usuario = usuarioRepository.findById(usuarioDto.getId())
                .orElseThrow(() -> new RuntimeException("MSG_USUARIO"));
        usuarioSpec.verifyEmailEmUso(usuario, usuarioDto);
        usuario = converterUsuarioDtoParaUsuario(usuarioDto);
        usuarioRepository.save(usuario);
        return  converterUsuarioParaUsuarioDto(usuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("MSG_USUARIO"));
        return converterUsuarioParaUsuarioDto(usuario);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email);
        return converterUsuarioParaUsuarioDto(usuario);
    }
}

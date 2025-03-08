package Services;

import DTO.UsuarioDto;
import Models.Usuario;
import Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    public static final String MSG_USUARIO = "Usuario não encontrado";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDto converterUsuarioParaUsuarioDto(Usuario usuario){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSenha(usuario.getSenha());
        return usuarioDto;
    }

    public Usuario converterUsuarioDtoParaUsuario(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        return usuario;
    }

    public UsuarioDto cadastrarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = converterUsuarioDtoParaUsuario(usuarioDto);
        usuario = usuarioRepository.save(usuario);
        return converterUsuarioParaUsuarioDto(usuario);
    }

    public void deletarUsuario(UUID id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDto atualizarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = usuarioRepository.findById(usuarioDto.getId())
                .orElseThrow(() -> new RuntimeException("MSG_USUARIO"));
        usuario = converterUsuarioDtoParaUsuario(usuarioDto);
        usuarioRepository.save(usuario);
        return  converterUsuarioParaUsuarioDto(usuario);
    }

    public UsuarioDto buscarUsuarioPorId(UUID id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("MSG_USUARIO"));
        return converterUsuarioParaUsuarioDto(usuario);
    }

    public UsuarioDto buscarUsuarioPorEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email);
        return converterUsuarioParaUsuarioDto(usuario);
    }
}

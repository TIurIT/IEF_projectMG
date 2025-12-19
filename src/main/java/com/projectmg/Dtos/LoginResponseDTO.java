package com.projectmg.Dtos;

import com.projectmg.Enum.TipoAcesso;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String nome;
    private String email;
    private TipoAcesso tipoAcesso;
}

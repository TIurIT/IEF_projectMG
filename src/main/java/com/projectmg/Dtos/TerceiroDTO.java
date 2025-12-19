package com.projectmg.Dtos;

import com.projectmg.Enum.TipoServico;

public record TerceiroDTO (
     Long id,
     String nome,
     String bairro,
     String telefone,
     String cnpj,
     TipoServico tipoServico
){

}

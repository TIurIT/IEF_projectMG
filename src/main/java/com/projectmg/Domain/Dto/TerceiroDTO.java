package com.projectmg.Domain.Dto;

import com.projectmg.Domain.Enum.TipoServico;

public record TerceiroDTO (
     Long id,
     String nome,
     String bairro,
     String telefone,
     String cnpj,
     TipoServico tipoServico
){

}

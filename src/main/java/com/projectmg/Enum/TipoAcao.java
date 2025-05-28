package com.projectmg.Enum;

import com.projectmg.Models.Produto;

public enum TipoAcao {
    CRIADO("CRIADO"),
    ATUALIZADO("ATUALIZADO"),
    DELETADO("DELETADO");

    private final String acao;

   TipoAcao(String acao){
       this.acao = acao;
   }

    public String getAcao() {
        return acao;
    }
}

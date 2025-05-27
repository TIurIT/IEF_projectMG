package com.projectmg.Dto;

import com.projectmg.Models.Cliente;
import com.projectmg.Models.OrdemProducaoItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrdemProducaoDTO {
    private Long id;
    private Date dataDeCriacao;
    private Cliente cliente;
    private List<OrdemProducaoItem> itens;

    public OrdemProducaoDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OrdemProducaoItem> getItens() {
        return itens;
    }

    public void setItens(List<OrdemProducaoItem> itens) {
        this.itens = itens;
    }
}

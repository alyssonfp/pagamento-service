package com.vinhosales.pagamento.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class PagamentoDTO {

    private String id;
    private String pedidoId;
    private String status;
    private LocalDateTime dataCriacao;

    public PagamentoDTO() {}

    public PagamentoDTO(String id, String pedidoId, String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoDTO that = (PagamentoDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pedidoId, that.pedidoId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dataCriacao, that.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedidoId, status, dataCriacao);
    }
}

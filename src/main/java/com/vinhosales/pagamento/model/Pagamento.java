package com.vinhosales.pagamento.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "pagamentos")
public class Pagamento {

    @Id
    private String id;
    private String pedidoId;
    private String status;
    private LocalDateTime dataCriacao;

    public Pagamento() {
    }

    public Pagamento(String id, String pedidoId, String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public Pagamento(String pedidoId, String status) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.dataCriacao = LocalDateTime.now();
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
}

package com.vinhosales.pagamento.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PagamentoTest {

    @Test
    public void testPagamento_DefaultConstructor() {
        Pagamento pagamento = new Pagamento();

        assertNotNull(pagamento);
        pagamento.setId("id123");
        pagamento.setPedidoId("pedido123");
        pagamento.setStatus("DONE");
        pagamento.setDataCriacao(LocalDateTime.of(2024, 8, 8, 12, 0));

        assertEquals("id123", pagamento.getId());
        assertEquals("pedido123", pagamento.getPedidoId());
        assertEquals("DONE", pagamento.getStatus());
        assertEquals(LocalDateTime.of(2024, 8, 8, 12, 0), pagamento.getDataCriacao());
    }

    @Test
    public void testPagamento_ParameterizedConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Pagamento pagamento = new Pagamento("id123", "pedido123", "DONE", now);

        assertEquals("id123", pagamento.getId());
        assertEquals("pedido123", pagamento.getPedidoId());
        assertEquals("DONE", pagamento.getStatus());
        assertEquals(now, pagamento.getDataCriacao());
    }

    @Test
    public void testPagamento_PedidoIdAndStatusConstructor() {
        LocalDateTime before = LocalDateTime.now();
        Pagamento pagamento = new Pagamento("pedido123", "WAIT_PAYMENT");

        assertEquals("pedido123", pagamento.getPedidoId());
        assertEquals("WAIT_PAYMENT", pagamento.getStatus());
        assertNotNull(pagamento.getDataCriacao());
        assertEquals(pagamento.getDataCriacao().getDayOfYear(), before.getDayOfYear());
    }
}


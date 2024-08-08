package com.vinhosales.pagamento.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PagamentoDTOTest {

    @Test
    public void testPagamentoDTO_DefaultConstructor() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        assertNotNull(pagamentoDTO);
        pagamentoDTO.setId("id123");
        pagamentoDTO.setPedidoId("pedido123");
        pagamentoDTO.setStatus("DONE");
        pagamentoDTO.setDataCriacao(LocalDateTime.of(2024, 8, 8, 12, 0));

        assertEquals("id123", pagamentoDTO.getId());
        assertEquals("pedido123", pagamentoDTO.getPedidoId());
        assertEquals("DONE", pagamentoDTO.getStatus());
        assertEquals(LocalDateTime.of(2024, 8, 8, 12, 0), pagamentoDTO.getDataCriacao());
    }

    @Test
    public void testPagamentoDTO_ParameterizedConstructor() {
        LocalDateTime now = LocalDateTime.now();
        PagamentoDTO pagamentoDTO = new PagamentoDTO("id123", "pedido123", "DONE", now);

        assertEquals("id123", pagamentoDTO.getId());
        assertEquals("pedido123", pagamentoDTO.getPedidoId());
        assertEquals("DONE", pagamentoDTO.getStatus());
        assertEquals(now, pagamentoDTO.getDataCriacao());
    }
}


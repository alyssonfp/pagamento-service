package com.vinhosales.pagamento.controller;

import com.vinhosales.pagamento.dto.PagamentoDTO;
import com.vinhosales.pagamento.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PagamentoControllerTest {

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(pagamentoController).build();
    }

    @Test
    void testProcessarPagamento() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO("id123", "pedido123", "DONE", null);
        when(pagamentoService.processarPagamento(anyString())).thenReturn(Mono.just(pagamentoDTO));

        webTestClient.post().uri("/pagamentos/processar/pedido123")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PagamentoDTO.class)
                .isEqualTo(pagamentoDTO);
    }

    @Test
    void testGetPagamentoById() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO("id123", "pedido123", "DONE", null);
        when(pagamentoService.getPagamentoById(anyString())).thenReturn(Mono.just(pagamentoDTO));

        webTestClient.get().uri("/pagamentos/123")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PagamentoDTO.class)
                .isEqualTo(pagamentoDTO);
    }

    @Test
    void testGetPagamentoById_NotFound() {
        when(pagamentoService.getPagamentoById(anyString())).thenReturn(Mono.empty());

        webTestClient.get().uri("/pagamentos/123")
                .exchange()
                .expectStatus().isNotFound();
    }
}

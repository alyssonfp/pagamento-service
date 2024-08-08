package com.vinhosales.pagamento.service;

import com.vinhosales.pagamento.dto.PagamentoDTO;
import com.vinhosales.pagamento.model.Pagamento;
import com.vinhosales.pagamento.repository.PagamentoRepository;
import com.vinhosales.pagamento.service.impl.PagamentoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PagamentoServiceImplTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PagamentoServiceImpl pagamentoService;

    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagamento = new Pagamento("1", "pedido123", "DONE", LocalDateTime.now());
    }

    @Test
    void testProcessarPagamento_Success() {
        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(Mono.just(pagamento));

        Mono<PagamentoDTO> result = pagamentoService.processarPagamento("pedido123");

        StepVerifier.create(result)
                .expectNextMatches(dto -> dto.getPedidoId().equals("pedido123") && (dto.getStatus().equals("DONE") || dto.getStatus().equals("ERROR_PAYMENT")))
                .verifyComplete();

        verify(pagamentoRepository, times(1)).save(any(Pagamento.class));
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), any(PagamentoDTO.class));
    }

    @Test
    void testProcessarPagamento_InvalidPedidoId() {
        Mono<PagamentoDTO> result = pagamentoService.processarPagamento("");

        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    void testGetPagamentoById_Success() {
        when(pagamentoRepository.findById(anyString())).thenReturn(Mono.just(pagamento));

        Mono<PagamentoDTO> result = pagamentoService.getPagamentoById("1");

        StepVerifier.create(result)
                .expectNextMatches(dto -> dto.getId().equals("1") && dto.getPedidoId().equals("pedido123"))
                .verifyComplete();
    }

    @Test
    void testGetPagamentoById_NotFound() {
        when(pagamentoRepository.findById(anyString())).thenReturn(Mono.empty());

        Mono<PagamentoDTO> result = pagamentoService.getPagamentoById("1");

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }
}


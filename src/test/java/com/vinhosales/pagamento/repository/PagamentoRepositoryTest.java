package com.vinhosales.pagamento.repository;

import com.vinhosales.pagamento.model.Pagamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@DataMongoTest
public class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Test
    public void testSavePagamento() {
        Pagamento pagamento = new Pagamento("1", "pedido123", "WAIT_PAYMENT", LocalDateTime.now());

        Mono<Pagamento> savedPagamento = pagamentoRepository.save(pagamento);

        StepVerifier.create(savedPagamento)
                .expectNextMatches(p -> p.getId().equals("1") && p.getPedidoId().equals("pedido123"))
                .verifyComplete();
    }

    @Test
    public void testFindById() {
        Pagamento pagamento = new Pagamento("1", "pedido123", "DONE", LocalDateTime.now());

        pagamentoRepository.save(pagamento).block();

        Mono<Pagamento> foundPagamento = pagamentoRepository.findById("1");

        StepVerifier.create(foundPagamento)
                .expectNextMatches(p -> p.getId().equals("1") && p.getStatus().equals("DONE"))
                .verifyComplete();
    }

    @Test
    public void testDeleteById() {
        Pagamento pagamento = new Pagamento("1", "pedido123", "ERROR_PAYMENT", LocalDateTime.now());

        pagamentoRepository.save(pagamento).block();

        Mono<Void> deleteMono = pagamentoRepository.deleteById("1");

        StepVerifier.create(deleteMono)
                .verifyComplete();

        StepVerifier.create(pagamentoRepository.findById("1"))
                .expectNextCount(0)
                .verifyComplete();
    }
}


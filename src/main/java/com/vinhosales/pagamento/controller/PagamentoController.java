package com.vinhosales.pagamento.controller;

import com.vinhosales.pagamento.dto.PagamentoDTO;
import com.vinhosales.pagamento.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/processar/{pedidoId}")
    public Mono<ResponseEntity<PagamentoDTO>> processarPagamento(@PathVariable String pedidoId) {
        return pagamentoService.processarPagamento(pedidoId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PagamentoDTO>> getPagamentoById(@PathVariable String id) {
        return pagamentoService.getPagamentoById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

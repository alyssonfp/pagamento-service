package com.vinhosales.pagamento.service;

import com.vinhosales.pagamento.dto.PagamentoDTO;
import reactor.core.publisher.Mono;

public interface PagamentoService {

    Mono<PagamentoDTO> processarPagamento(String pedidoId);

    Mono<PagamentoDTO> getPagamentoById(String id);
}

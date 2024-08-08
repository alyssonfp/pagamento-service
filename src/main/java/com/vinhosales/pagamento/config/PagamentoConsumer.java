package com.vinhosales.pagamento.config;

import com.vinhosales.pagamento.dto.PagamentoDTO;
import com.vinhosales.pagamento.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PagamentoConsumer {

    private final PagamentoService pagamentoService;

    public PagamentoConsumer(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = "pedidos.queue")
    public void consumeMessage(PagamentoDTO pagamentoDTO) {
        if (pagamentoDTO.getPedidoId() == null || pagamentoDTO.getPedidoId().trim().isEmpty()) {
            throw new IllegalArgumentException("PedidoId não pode ser nulo ou vazio");
        }
        pagamentoService.processarPagamento(pagamentoDTO.getPedidoId())
                .subscribe();
    }
}

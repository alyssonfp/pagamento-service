package com.vinhosales.pagamento.service.impl;

import com.vinhosales.pagamento.dto.PagamentoDTO;
import com.vinhosales.pagamento.model.Pagamento;
import com.vinhosales.pagamento.repository.PagamentoRepository;
import com.vinhosales.pagamento.service.PagamentoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final RabbitTemplate rabbitTemplate;

    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository, RabbitTemplate rabbitTemplate) {
        this.pagamentoRepository = pagamentoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Mono<PagamentoDTO> processarPagamento(String pedidoId) {
        if (pedidoId == null || pedidoId.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("PedidoId não pode ser nulo nem vazio"));
        }

        String status = new Random().nextBoolean() ? "DONE" : "ERROR_PAYMENT";

        Pagamento pagamento = new Pagamento(pedidoId, status);

        return pagamentoRepository.save(pagamento)
                .map(this::toDTO)
                .doOnSuccess(pagamentoDTO -> {
                    rabbitTemplate.convertAndSend(
                            "pagamento.exchange",
                            "pagamento.routingKey",
                            pagamentoDTO
                    );
                })
                .doOnError(error -> {
                     System.err.println("Erro ao processar pagamento: " + error.getMessage());
                });
    }

    @Override
    public Mono<PagamentoDTO> getPagamentoById(String id) {
        return pagamentoRepository.findById(id)
                .map(this::toDTO);
    }

    private PagamentoDTO toDTO(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo");
        }

        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getStatus(),
                pagamento.getDataCriacao()
        );
    }

    private Pagamento toEntity(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(pagamentoDTO.getId());
        pagamento.setPedidoId(pagamentoDTO.getPedidoId());
        pagamento.setStatus(pagamentoDTO.getStatus());
        pagamento.setDataCriacao(pagamentoDTO.getDataCriacao());
        return pagamento;
    }
}

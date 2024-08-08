package com.vinhosales.pagamento.repository;

import com.vinhosales.pagamento.model.Pagamento;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends ReactiveMongoRepository<Pagamento, String> {

}

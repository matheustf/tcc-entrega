package com.puc.tcc.entrega.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.puc.tcc.entrega.model.Entrega;


@Repository
public interface EntregaRepository extends MongoRepository<Entrega, String> {

	Optional<Entrega> findByCodigoDaEntrega(String codigoDaEntrega);

	Optional<Entrega> findByIdCompra(String idCompra);

}

package com.puc.tcc.entrega.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.puc.tcc.entrega.model.Avaliacao;


@Repository
public interface AvaliacaoRepository extends MongoRepository<Avaliacao, String> {

}

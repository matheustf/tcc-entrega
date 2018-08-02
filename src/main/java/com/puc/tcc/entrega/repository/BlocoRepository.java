package com.puc.tcc.entrega.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.puc.tcc.entrega.model.Bloco;


@Repository
public interface BlocoRepository extends MongoRepository<Bloco, String> {

    Optional<Bloco> findByNome(String nome);
    
}

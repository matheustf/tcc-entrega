package com.puc.tcc.entrega.mockcorreios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MockCorreiosRepository extends MongoRepository<MockCorreios, String> {

}

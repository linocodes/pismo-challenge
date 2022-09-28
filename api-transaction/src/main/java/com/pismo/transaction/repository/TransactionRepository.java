package com.pismo.transaction.repository;

import com.pismo.transaction.entity.TransactiontEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactiontEntity, Long> {

}

package com.gala.gaetano.openapidemo.app.repository;

import com.gala.gaetano.openapidemo.app.model.Transaction;
import org.springframework.data.repository.CrudRepository;


public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}

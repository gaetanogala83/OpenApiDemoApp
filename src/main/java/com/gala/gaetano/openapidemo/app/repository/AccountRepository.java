package com.gala.gaetano.openapidemo.app.repository;

import com.gala.gaetano.openapidemo.app.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Optional<Account> findAccountByAccountNumber(String accountNumber);
}

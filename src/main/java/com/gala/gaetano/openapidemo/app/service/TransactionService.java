package com.gala.gaetano.openapidemo.app.service;

import com.gala.gaetano.openapidemo.api.model.Accounts;
import com.gala.gaetano.openapidemo.app.model.Account;
import com.gala.gaetano.openapidemo.app.model.Transaction;
import com.gala.gaetano.openapidemo.app.repository.AccountRepository;
import com.gala.gaetano.openapidemo.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> retrieveAllTransactions(){

        List<Transaction> retrievedTransactions = new ArrayList<Transaction>(){{
            transactionRepository.findAll().forEach(this::add);
        }};

        if(retrievedTransactions.isEmpty())
            return null;

        return retrievedTransactions;
    }

    public Transaction getTransaction(Integer transactionId) {

        if(transactionId == null)
            return null;

        Optional<Transaction> transactionOpt = transactionRepository.findById(transactionId);

        if(transactionOpt.isEmpty())
            return null;

        return transactionOpt.get();
    }


    public List<Transaction> getTransactionsByAccountId(String accountId) {

        if(accountId == null)
            return null;

        Optional<Account> retrievedAccount = accountRepository.findAccountByAccountNumber(accountId);

        if(retrievedAccount.isEmpty() || retrievedAccount.get().getTransactions() == null || retrievedAccount.get().getTransactions().isEmpty())
            return null;

        return retrievedAccount.get().getTransactions();
    }
}

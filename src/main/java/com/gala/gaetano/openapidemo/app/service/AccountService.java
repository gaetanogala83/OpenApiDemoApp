package com.gala.gaetano.openapidemo.app.service;

import com.gala.gaetano.openapidemo.api.model.Accounts;
import com.gala.gaetano.openapidemo.app.model.Account;
import com.gala.gaetano.openapidemo.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Accounts retrieveAllAccounts(){

        List<Account> retrievedAccounts = new ArrayList<Account>(){{
            accountRepository.findAll().forEach(this::add);
        }};


        if(retrievedAccounts.isEmpty())
            return null;

        Accounts accounts = new Accounts();

        for (Account account: retrievedAccounts) {
            com.gala.gaetano.openapidemo.api.model.Account outputAccount = new com.gala.gaetano.openapidemo.api.model.Account();
            outputAccount.setAccountNumber(account.getAccountNumber());
            outputAccount.setAmount(account.getAmount());
            accounts.add(outputAccount);
        }

        return accounts;
    }

    public Account retrieveAccountById(Integer id){

        Optional<Account> clientOpt = accountRepository.findById(id);

        return clientOpt.orElse(null);
    }

    public Boolean deleteAccountById(Integer id){

        Account client = retrieveAccountById(id);
        if(client == null)
            return false;

        accountRepository.deleteById(id);

        return true;
    }

    public Account saveAccount(Account accountToSave){

        if(accountToSave.getAccountNumber() == null)
            return null;

        Optional<Account> checked = accountRepository.findClientByAccountNumber(accountToSave.getAccountNumber());

        if(checked.isPresent())
            return null;

        return accountRepository.save(accountToSave);
    }

    public Account updateAccount(Account account){

        if(account.getAccountNumber() == null)
            return null;

        Optional<Account> retrievedAccount = accountRepository.findClientByAccountNumber(account.getAccountNumber());

        if(retrievedAccount.isEmpty())
            return null;

        Account accountToUpdate = retrievedAccount.get();
        if(accountToUpdate.getAmount() != null){
            BigDecimal newAmount = accountToUpdate.getAmount().add(account.getAmount());
            accountToUpdate.setAmount(newAmount);
        }else
            accountToUpdate.setAmount(account.getAmount());

        return accountRepository.save(accountToUpdate);
    }



}

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

    public com.gala.gaetano.openapidemo.api.model.Account retrieveAccountById(String accountId){

        Optional<Account> clientOpt = accountRepository.findAccountByAccountNumber(accountId);

        if(clientOpt.isEmpty())
            return null;

        com.gala.gaetano.openapidemo.api.model.Account accountToReturn = new com.gala.gaetano.openapidemo.api.model.Account();

        accountToReturn.setAccountNumber(clientOpt.get().getAccountNumber());
        accountToReturn.setAmount(clientOpt.get().getAmount());

        return accountToReturn;

    }

    public Boolean deleteAccountById(String accountId){

        Optional<Account> accountOpt = accountRepository.findAccountByAccountNumber(accountId);
        if(accountOpt.isEmpty())
            return false;

        accountRepository.deleteById(accountOpt.get().getId());

        return true;
    }

    public Account saveAccount(Account accountToSave){

        if(accountToSave.getAccountNumber() == null)
            return null;

        Optional<Account> checked = accountRepository.findAccountByAccountNumber(accountToSave.getAccountNumber());

        if(checked.isPresent())
            return null;

        return accountRepository.save(accountToSave);
    }

    public com.gala.gaetano.openapidemo.api.model.Account updateAccount(Account account){

        if(account.getAccountNumber() == null)
            return null;

        Optional<Account> retrievedAccount = accountRepository.findAccountByAccountNumber(account.getAccountNumber());

        if(retrievedAccount.isEmpty())
            return null;

        BigDecimal retrievedAmount = retrievedAccount.get().getAmount();
        if(retrievedAmount != null){
            retrievedAccount.get().setAmount(retrievedAmount.add(account.getAmount()));
        }else {
            retrievedAccount.get().setAmount(account.getAmount());
        }

        Account updatedAccount = accountRepository.save(retrievedAccount.get());

        com.gala.gaetano.openapidemo.api.model.Account accountToUpdate = new com.gala.gaetano.openapidemo.api.model.Account();

        accountToUpdate.setAccountNumber(updatedAccount.getAccountNumber());
        accountToUpdate.setAmount(updatedAccount.getAmount());

        return accountToUpdate;
    }



}

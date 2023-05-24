package com.gala.gaetano.openapidemo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gala.gaetano.openapidemo.api.AccountsApi;
import com.gala.gaetano.openapidemo.api.model.*;
import com.gala.gaetano.openapidemo.app.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v2/account/")
@Slf4j
public class AccountController implements AccountsApi{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<String> keepAlive() {
        LOGGER.info("Calling the KeepAlive API!");
        LOGGER.debug("Calling the KeepAlive API DEBUG LOGGING MODE!");

        return new ResponseEntity<>("The ACCOUNT Controller is ALIVE", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> getAccount(String accountId) {

        if(accountId.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        Account account = accountService.retrieveAccountById(accountId);

        if(account == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Accounts> getAccounts() {
        LOGGER.info("Calling the getAccounts API!");

        Accounts accounts = accountService.retrieveAllAccounts();

        if (accounts == null || accounts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createAccount(NewAccountRequest body) {

        if(body.getAccountNumber() == null || body.getAmount() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        com.gala.gaetano.openapidemo.app.model.Account accountToSave = new com.gala.gaetano.openapidemo.app.model.Account();
        accountToSave.setAccountNumber(body.getAccountNumber());
        accountToSave.setAmount(body.getAmount());

        com.gala.gaetano.openapidemo.app.model.Account savedAccount = accountService.saveAccount(accountToSave);

        if(savedAccount == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @Override
    public ResponseEntity<Account> putAccount(String accountId, Amount body) {

        if(accountId == null || body.getAmount() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        com.gala.gaetano.openapidemo.app.model.Account accountToUpdate = new com.gala.gaetano.openapidemo.app.model.Account();
        accountToUpdate.setAccountNumber(accountId);
        accountToUpdate.setAmount(body.getAmount());

        Account retrievedAccount = accountService.updateAccount(accountToUpdate);

        if(retrievedAccount == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();

        return new ResponseEntity<>(retrievedAccount, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAccount(String accountId) {

        if(accountId == null || accountId.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        if(!accountService.deleteAccountById(accountId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

}

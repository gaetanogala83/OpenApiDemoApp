package com.gala.gaetano.openapidemo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gala.gaetano.openapidemo.api.AccountsApi;
import com.gala.gaetano.openapidemo.api.model.*;
import com.gala.gaetano.openapidemo.app.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/account/")
@Slf4j
public class AccountController implements AccountsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountService accountService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.of(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> deleteAccount(String accountId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> depositToAccount(DepositRequest body) {
        return null;
    }

    @Override
    public ResponseEntity<Account> getAccount(String accountId) {
        return null;
    }

    @Override
    public ResponseEntity<Accounts> getAccounts(){
        LOGGER.info("Calling the getAccounts API!");

        Accounts accounts = accountService.retrieveAllAccounts();
/*

        if(accounts == null)
            throw new NoDataException((new NoDataFoundError()).code(HttpStatus.NOT_FOUND.value()).message("No accounts are available"));
*/
        return new ResponseEntity<Accounts>(accounts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> keepAlive() {
        LOGGER.info("Calling the KeepAlive API!");
        LOGGER.debug("Calling the KeepAlive API DEBUG LOGGING MODE!");

        return new ResponseEntity<>("The ACCOUNT Controller is ALIVE", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> putAccount(String accountId, Amount body) {
        return null;
    }

}

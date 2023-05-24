package com.gala.gaetano.openapidemo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gala.gaetano.openapidemo.api.AccountsApi;
import com.gala.gaetano.openapidemo.api.model.Account;
import com.gala.gaetano.openapidemo.api.model.Accounts;
import com.gala.gaetano.openapidemo.api.model.Amount;
import com.gala.gaetano.openapidemo.api.model.NewAccountRequest;
import com.gala.gaetano.openapidemo.app.model.Transaction;
import com.gala.gaetano.openapidemo.app.service.AccountService;
import com.gala.gaetano.openapidemo.app.service.TransactionService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v2/transaction/")
@Slf4j
public class TransactionController{

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/keepalive")
    public ResponseEntity<String> keepAlive() {
        LOGGER.info("Calling the KeepAlive API!");
        LOGGER.debug("Calling the KeepAlive API DEBUG LOGGING MODE!");

        return new ResponseEntity<>("The TRANSACTION Controller is ALIVE", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<Transaction>> getTransactions() {

        List<Transaction> transactionList = transactionService.retrieveAllTransactions();

        if(transactionList == null || transactionList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();

        transactionList.forEach(transaction -> {
            Link selfLink = linkTo(TransactionController.class).slash("account").slash(transaction.getAccount().getAccountNumber()).withSelfRel();
            transaction.add(selfLink);
        });

        transactionList.forEach(transaction -> {
            Link selfLink = linkTo(AccountController.class).slash(transaction.getAccount().getAccountNumber()).withSelfRel();
            transaction.add(selfLink);
        });

        CollectionModel<Transaction> result = CollectionModel.of(transactionList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Integer transactionId) {
        LOGGER.info("Calling the getTransaction API!");

        Transaction transaction = transactionService.getTransaction(transactionId);

        if (transaction == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();



        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getAccountByAccountId(@PathVariable String accountId) {

        if(accountId == null || accountId.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        List<Transaction> transactionList = transactionService.getTransactionsByAccountId(accountId);

        if(transactionList == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }
}

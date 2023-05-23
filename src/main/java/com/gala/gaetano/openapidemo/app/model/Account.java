package com.gala.gaetano.openapidemo.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@SequenceGenerator(name= "ACCOUNTS_SEQUENCE", sequenceName = "ACCOUNTS_SEQUENCE_ID", allocationSize = 1)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNTS_SEQUENCE")
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String accountNumber;
    @Getter
    @Setter
    private BigDecimal amount;
}

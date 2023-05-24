package com.gala.gaetano.openapidemo.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
@SequenceGenerator(name= "ACCOUNTS_SEQUENCE", sequenceName = "ACCOUNTS_SEQUENCE_ID", allocationSize = 1)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Account extends RepresentationModel<Account> {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNTS_SEQUENCE")
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String accountNumber;
    @Getter
    @Setter
    private BigDecimal amount;

    @OneToMany(mappedBy = "account",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    @Getter
    @Setter
    private List<Transaction> transactions;

}

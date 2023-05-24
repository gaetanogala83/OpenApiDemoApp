package com.gala.gaetano.openapidemo.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transactions")
@SequenceGenerator(name= "TRANSACTIONS_SEQUENCE", sequenceName = "TRANSACTIONS_SEQUENCE_ID", allocationSize = 1)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Transaction extends RepresentationModel<Transaction> {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSACTIONS_SEQUENCE")
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate data;
    @Getter
    @Setter
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @Getter
    @Setter
    private Account account;
    @Transient
    @Getter
    @Setter
    private Integer accountId;

}

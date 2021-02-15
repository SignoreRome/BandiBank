package com.company.BandiBank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "creditcards")
@Data
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creditcard_seq")
    @SequenceGenerator(name = "creditcard_seq", sequenceName = "creditcard_seq", allocationSize = 1)
    @Column(name = "number")
    private Long number;
    @Column(name = "valid_thru")
    private Date validThru;
    @Column(name = "balance")
    private int balance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountCC;
}

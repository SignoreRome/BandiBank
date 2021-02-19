package com.company.BandiBank.Entity;

import com.company.BandiBank.API.Transfer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "creditcards")
@Getter
@Setter
@NoArgsConstructor
public class CreditCard implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creditcard_seq")
    @SequenceGenerator(name = "creditcard_seq", sequenceName = "creditcard_seq", allocationSize = 1)
    @Column(name = "number")
    private Long number;
    @Column(name = "valid_thru")
    private Date validThru = new Date();
    @Column(name = "balance")
    private Integer balance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountCC;

    public CreditCard(int balance) {
        this.balance = balance;
    }

    public CreditCard(int balance, Account accountCC) {
        this.balance = balance;
        this.accountCC = accountCC;
    }
}

package com.company.BandiBank.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "debitcards")
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debitcard_seq")
    @SequenceGenerator(name = "debitcard_seq", sequenceName = "debitcard_seq", allocationSize = 1)
    @Column(name = "number")
    private Long number;
    @Column(name = "valid_thru")
    private Date validThru = new Date();
    @Column(name = "balance")
    private int balance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountDC;

    public DebitCard(int balance, Account accountDC) {
        this.balance = balance;
        this.accountDC = accountDC;
    }

    public DebitCard(Date validThru, int balance, Account accountDC) {
        this.validThru = validThru;
        this.balance = balance;
        this.accountDC = accountDC;
    }
}

package com.company.BandiBank.Entity;

import com.company.BandiBank.API.Transfer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "deposits")
@Getter
@Setter
@NoArgsConstructor
public class Deposit implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private Long number;
    @Column(name = "balance")
    private int balance;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account accountDP;

    public Deposit(int balance, Account accountDP) {
        this.balance = balance;
        this.accountDP = accountDP;
    }
}

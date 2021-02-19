package com.company.BandiBank.Entity;

import com.company.BandiBank.API.Transfer;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Table(name = "accountcards")
public class View implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "number")
    private Long number;
    @Column(name = "balance")
    private int balance;

    @Override
    public Long getNumber() {
        return number;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}

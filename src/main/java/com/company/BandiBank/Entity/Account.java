package com.company.BandiBank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_time")
    private Date createdTime = new Date();
    @OneToMany(mappedBy = "accountCC")
    private List<CreditCard> creditCards = new ArrayList<>();
    @OneToMany(mappedBy = "accountDC")
    private List<DebitCard> debitCards = new ArrayList<>();
    @OneToOne(mappedBy = "accountDP")
    private Deposit deposit;

    public Account(Date createdTime) {
        this.createdTime = createdTime;
    }

}
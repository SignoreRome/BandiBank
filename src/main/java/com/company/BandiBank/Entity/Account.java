package com.company.BandiBank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "balance")
    private int balance;
    @OneToMany(mappedBy = "accountCC")
    private List<CreditCard> creditCards;
    @OneToMany(mappedBy = "accountDC")
    private List<DebitCard> debitCards;
}

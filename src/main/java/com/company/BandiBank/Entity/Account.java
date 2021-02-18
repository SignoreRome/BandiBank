package com.company.BandiBank.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_time")
    private Date createdTime = new Date();
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

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
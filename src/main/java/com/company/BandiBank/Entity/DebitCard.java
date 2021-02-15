package com.company.BandiBank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
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
    private Date validThru;
    @Column(name = "balance")
    private int balance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountDC;
}

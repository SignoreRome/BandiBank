package com.company.BandiBank.Entity;

import com.company.BandiBank.API.Transfer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "creditcards")
@Data
@NoArgsConstructor
@NamedEntityGraph(name = "CreditCard", attributeNodes = {@NamedAttributeNode("accountCC")})
public class CreditCard implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creditcard_seq")
    @SequenceGenerator(name = "creditcard_seq", sequenceName = "creditcard_seq", allocationSize = 1)
    @Column(name = "number")
    private Long number;
    @Column(name = "valid_thru")
    private Date validThru = new Date();
    @Column(name = "balance")
    private int balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account accountCC;
}

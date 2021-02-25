package com.company.BandiBank.Entity;

import com.company.BandiBank.API.Transfer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "debitcards")
public class DebitCard implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debitcard_seq")
    @SequenceGenerator(name = "debitcard_seq", sequenceName = "debitcard_seq", allocationSize = 1)
    @Column(name = "number")
    private Long number;
    @Column(name = "valid_thru")
    private Date validThru = new Date();
    @Column(name = "balance")
    private int balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account accountDC;
}

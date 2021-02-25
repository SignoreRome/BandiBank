package com.company.BandiBank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "account-only-entity-graph"),
        @NamedEntityGraph(name = "account-creditCards-entity-graph",
                attributeNodes = {@NamedAttributeNode("debitCards")}),
        @NamedEntityGraph(name = "account-debitCards-entity-graph",
                attributeNodes = {@NamedAttributeNode("creditCards")}),
        @NamedEntityGraph(name = "account-deposit-entity-graph",
                attributeNodes = {@NamedAttributeNode("deposit")}),

})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_time")
    private Date createdTime = new Date();
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "accountCC", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CreditCard> creditCards = new ArrayList<>();
    @OneToMany(mappedBy = "accountDC", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DebitCard> debitCards = new ArrayList<>();
    @OneToOne(mappedBy = "accountDP", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Deposit deposit;
}
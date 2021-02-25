package com.company.BandiBank.Repository;

import com.company.BandiBank.Entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    @EntityGraph(value = )
//    Optional<Account> findById(Long id);

    @Query("select sum(coalesce(d.balance,0)) + sum(coalesce(cc.balance, 0)) + sum(coalesce(dc.balance, 0)) from Account Acc " +
            "left join Deposit d on Acc.id = d.accountDP.id " +
            "left join CreditCard cc on Acc.id = cc.accountCC.id " +
            "left join DebitCard dc on Acc.id = dc.accountDC.id " +
            "where Acc.id=?1")
    Integer sumBalance(Long id);
}

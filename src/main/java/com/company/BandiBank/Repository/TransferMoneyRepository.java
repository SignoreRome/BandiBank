package com.company.BandiBank.Repository;

import com.company.BandiBank.API.Transfer;
import com.company.BandiBank.Entity.TransferMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransferMoneyRepository extends JpaRepository<TransferMoney, Long> {
    @Query("select v.accountId, v.number, v.balance from View v where v.accountId = ?1")
    List<Transfer> listCards(Long idAcc);

    @Query("select v.accountId, v.number, v.balance from View v where v.number = ?1")
    Optional<Transfer> findCard(Long num);
}

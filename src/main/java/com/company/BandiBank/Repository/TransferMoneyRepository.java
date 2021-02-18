package com.company.BandiBank.Repository;

import com.company.BandiBank.Entity.TransferMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferMoneyRepository extends JpaRepository<TransferMoney, Long> {
}

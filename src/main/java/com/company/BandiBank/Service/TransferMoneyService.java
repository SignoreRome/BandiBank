package com.company.BandiBank.Service;

import com.company.BandiBank.API.Transfer;
import com.company.BandiBank.Entity.Account;
import com.company.BandiBank.Entity.Deposit;
import com.company.BandiBank.Entity.TransferMoney;
import com.company.BandiBank.Exceptions.NotEnoughMoneyException;

import java.util.List;

public interface TransferMoneyService {
    boolean innerTransferMoneyFromTo(Long idAcc, Long fromNum, Long toNum, int transferSize);
    boolean innerTransaction(Long idAcc, TransferMoney transaction);

    boolean outerTransferMoneyFromTo(Long numFromDep, String phoneTo, int transferSize) throws NotEnoughMoneyException;
    boolean outerTransaction(Long id, TransferMoney transaction);

    TransferMoney save(TransferMoney transferMoney);
    List<TransferMoney> findAll();
}

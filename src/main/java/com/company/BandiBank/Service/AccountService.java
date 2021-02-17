package com.company.BandiBank.Service;

import com.company.BandiBank.Entity.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> showAcc(Long id);
    Account createAcc(Account account);
    Account saveAcc (Account account);
    Integer sumBalance(Long id);
}

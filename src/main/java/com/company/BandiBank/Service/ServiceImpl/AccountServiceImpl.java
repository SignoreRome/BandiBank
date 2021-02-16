package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.Entity.Account;
import com.company.BandiBank.Repository.AccountRepository;
import com.company.BandiBank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> showAcc(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public Account createAcc(Account account) {
        return accountRepository.save(account);
    }
}

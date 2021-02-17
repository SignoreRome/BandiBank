package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.Entity.Deposit;
import com.company.BandiBank.Repository.DepositRepository;
import com.company.BandiBank.Service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepositServiceImpl implements DepositService {
    private DepositRepository depositRepository;

    @Autowired
    public DepositServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    @Transactional
    public Deposit createDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }
}

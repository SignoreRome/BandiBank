package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.Repository.DepositRepository;
import com.company.BandiBank.Service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImpl implements DepositService {
    private DepositRepository depositRepository;

    @Autowired
    public DepositServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }
}

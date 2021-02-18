package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.Entity.CreditCard;
import com.company.BandiBank.Repository.CreditCardRepository;
import com.company.BandiBank.Service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CreditCardServiceImpl implements CreditCardService {
    private CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public CreditCard createCC(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public Optional<CreditCard> findByIdCC(Long id) {
        return creditCardRepository.findById(id);
    }
}

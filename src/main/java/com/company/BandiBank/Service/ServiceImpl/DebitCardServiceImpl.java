package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.Entity.DebitCard;
import com.company.BandiBank.Repository.DebitCardRepository;
import com.company.BandiBank.Service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DebitCardServiceImpl implements DebitCardService {
    private DebitCardRepository debitCardRepository;

    @Autowired
    public DebitCardServiceImpl(DebitCardRepository debitCardRepository) {
        this.debitCardRepository = debitCardRepository;
    }

    @Override
    @Transactional
    public DebitCard createDC(DebitCard debitCard) {
        return debitCardRepository.save(debitCard);
    }
}

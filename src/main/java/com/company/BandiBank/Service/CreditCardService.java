package com.company.BandiBank.Service;

import com.company.BandiBank.Entity.CreditCard;

import java.util.Optional;

public interface CreditCardService {
    CreditCard createCC(CreditCard creditCard);
    Optional<CreditCard> findByIdCC (Long id);
}

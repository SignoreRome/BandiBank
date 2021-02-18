package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.API.Transfer;
import com.company.BandiBank.Entity.Account;
import com.company.BandiBank.Entity.Deposit;
import com.company.BandiBank.Entity.TransferMoney;
import com.company.BandiBank.Entity.User;
import com.company.BandiBank.Exceptions.NotEnoughMoneyException;
import com.company.BandiBank.Repository.*;
import com.company.BandiBank.Service.TransferMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class TransferMoneyServiceImpl implements TransferMoneyService {

    private DepositRepository depositRepository;
    private DebitCardRepository debitCardRepository;
    private CreditCardRepository creditCardRepository;
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private TransferMoneyRepository transferMoneyRepository;

    @Autowired
    public TransferMoneyServiceImpl(DepositRepository depositRepository, DebitCardRepository debitCardRepository,
                                    CreditCardRepository creditCardRepository, AccountRepository accountRepository,
                                    UserRepository userRepository, TransferMoneyRepository transferMoneyRepository) {
        this.depositRepository = depositRepository;
        this.debitCardRepository = debitCardRepository;
        this.creditCardRepository = creditCardRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transferMoneyRepository = transferMoneyRepository;
    }

    //Нерабочий
    @Override
    @Transactional(readOnly = false)
    public boolean innerTransferMoneyFromTo(Long idAcc, Long fromNum, Long toNum, int transferSize) {
//        if (fromNum == null || toNum == null)
//            throw new NullPointerException();
//        Account account = accountRepository.findById(idAcc).get();
//        List<Long> listIdCC = account.getCreditCards().stream()
//                .map(e->e.getNumber()).collect(Collectors.toList());
//        List<Long> listIdDC = account.getDebitCards().stream()
//                .map(e->e.getNumber()).collect(Collectors.toList());
//        Long idDP = account.getDeposit().getNumber();
//        Set<Long> idSet = Stream.of(listIdCC, listIdCC)
//                .flatMap(Collection::stream).collect(Collectors.toSet());
//        idSet.add(idDP);
//
//        if (idSet.contains(fromNum)&&idSet.contains(toNum)){
//
//        }
        return false;
    }

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(readOnly = false)
    public boolean outerTransferMoneyFromTo(Long numFromDep, String phoneTo, int transferSize) throws NotEnoughMoneyException {

        Deposit depositFrom = depositRepository.findById(numFromDep).get();
        if (depositFrom.getBalance() < transferSize)
            throw new NotEnoughMoneyException();

        User userTo = userRepository.findByPhone(phoneTo).get();
        Deposit depositTo = userTo.getAccount().getDeposit();
        depositFrom.setBalance(depositFrom.getBalance() - transferSize);
        depositTo.setBalance(depositTo.getBalance() + transferSize);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public TransferMoney save(TransferMoney transferMoney) {
        return transferMoneyRepository.save(transferMoney);
    }

    @Override
    public List<TransferMoney> findAll() {
        return transferMoneyRepository.findAll();
    }

}

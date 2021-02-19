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
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private TransferMoneyRepository transferMoneyRepository;

    @Autowired
    public TransferMoneyServiceImpl(DepositRepository depositRepository, AccountRepository accountRepository,
                                    UserRepository userRepository, TransferMoneyRepository transferMoneyRepository) {
        this.depositRepository = depositRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transferMoneyRepository = transferMoneyRepository;
    }

    //Нерабочий
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(readOnly = false)
    public boolean innerTransferMoneyFromTo(Long idAcc, Long fromNum, Long toNum, int transferSize) {

        Account account = accountRepository.findById(idAcc).get();
        Set<Transfer> transferSet = Stream.of(account.getCreditCards(), account.getDebitCards())
                .flatMap(Collection::stream).collect(Collectors.toSet());
        transferSet.add(account.getDeposit());

        try {
            Transfer cardFrom = transferSet.stream()
                    .filter(x -> x.getNumber().equals(fromNum)).findFirst().orElseThrow(() -> new NullPointerException("Карта отправитель не найдена"));
            Transfer cardTo = transferSet.stream()
                    .filter(x -> x.getNumber().equals(toNum)).findFirst().orElseThrow(() -> new NullPointerException("Карта получатель не найдена"));

            if (cardFrom.getBalance() < transferSize)
                throw new NotEnoughMoneyException("No money, bro");
            cardFrom.setBalance(cardFrom.getBalance() - transferSize);
            cardTo.setBalance(cardTo.getBalance() + transferSize);
            return true;
        } catch (NullPointerException | NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
            return false;
        }

//        Нерабочая идея оказалась, из-за view
//        try{
//            Transfer cardFrom = transferMoneyRepository.findCard(fromNum).orElseThrow(()->new NullPointerException("Не выгрузил, бро"));
//            Transfer cardTo = transferMoneyRepository.findCard(toNum).orElseThrow(()->new NullPointerException("Не выгрузил, бро"));
//            if (cardFrom.getBalance() < transferSize)
//                throw new NotEnoughMoneyException("No money, bro");
//            cardFrom.setBalance(cardFrom.getBalance()-transferSize);
//            cardTo.setBalance(cardTo.getBalance()+transferSize);
//            return true;
//        }catch (NullPointerException | NotEnoughMoneyException e){
//            System.out.println(e.getMessage());
//            return false;
//        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean innerTransaction(Long idAcc, TransferMoney transaction) {
        Account account = accountRepository.findById(idAcc).get();
        String fromName = account.getUser().getName();
        transaction.setFromName(fromName);
        try {
            innerTransferMoneyFromTo(idAcc, transaction.getFromNum(), transaction.getToNum(), transaction.getTransferSize());
            save(transaction);
            return true;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(readOnly = false)
    public boolean outerTransferMoneyFromTo(Long numFromDep, String phoneTo, int transferSize) throws NotEnoughMoneyException {
        Deposit depositFrom = depositRepository.findById(numFromDep).get();
        if (depositFrom.getBalance() < transferSize)
            throw new NotEnoughMoneyException("Недостаточно денег для перевода" + (transferSize - depositFrom.getBalance()));

        User userTo = userRepository.findByPhone(phoneTo).get();
        Deposit depositTo = userTo.getAccount().getDeposit();
        depositFrom.setBalance(depositFrom.getBalance() - transferSize);
        depositTo.setBalance(depositTo.getBalance() + transferSize);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean outerTransaction(Long id, TransferMoney transaction) {
        Account account = accountRepository.findById(id).get();
        Long numFrom = account.getDeposit().getNumber();
        String nameFrom = account.getUser().getName();
        transaction.setFromName(nameFrom);
        transaction.setFromNum(numFrom);
        try {
            outerTransferMoneyFromTo(transaction.getFromNum(), transaction.getToPhone(), transaction.getTransferSize());
            save(transaction);
            return true;
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
            return false;
        }
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

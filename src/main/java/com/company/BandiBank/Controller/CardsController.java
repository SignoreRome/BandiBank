package com.company.BandiBank.Controller;

import com.company.BandiBank.Entity.Account;
import com.company.BandiBank.Entity.CreditCard;
import com.company.BandiBank.Entity.DebitCard;
import com.company.BandiBank.Entity.Deposit;
import com.company.BandiBank.Service.ServiceImpl.AccountServiceImpl;
import com.company.BandiBank.Service.ServiceImpl.CreditCardServiceImpl;
import com.company.BandiBank.Service.ServiceImpl.DebitCardServiceImpl;
import com.company.BandiBank.Service.ServiceImpl.DepositServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users/accounts")
public class CardsController {

    private CreditCardServiceImpl creditCardService;
    private DebitCardServiceImpl debitCardService;
    private DepositServiceImpl depositService;
    private AccountServiceImpl accountService;

    @Autowired
    public CardsController(CreditCardServiceImpl creditCardService, DebitCardServiceImpl debitCardService, DepositServiceImpl depositService, AccountServiceImpl accountService) {
        this.creditCardService = creditCardService;
        this.debitCardService = debitCardService;
        this.depositService = depositService;
        this.accountService = accountService;
    }

    @GetMapping("/{idAcc}/new_credit_card")
    public String addCredit(@PathVariable("idAcc") Long idAcc, Model model) {
        model.addAttribute("account", accountService.showAcc(idAcc).get());
        model.addAttribute("CC", new CreditCard());
        return "/creditNew";
    }

    @GetMapping("/{idAcc}/new_debit_card")
    public String addDebit(@PathVariable("idAcc") Long idAcc, Model model) {
        model.addAttribute("account", accountService.showAcc(idAcc).get());
        model.addAttribute("DC", new DebitCard());
        return "/debitNew";
    }

    @GetMapping("/{idAcc}/new_deposit")
    public String addDeposit(@PathVariable("idAcc") Long idAcc, Model model) {
        model.addAttribute("account", accountService.showAcc(idAcc).get());
        model.addAttribute("deposit", new Deposit());
        return "/depositNew";
    }

    @PostMapping("/{idAcc}/cards/credit_card")
    public String createCredit(@PathVariable("idAcc") Long idAcc,
                               @ModelAttribute("CC") CreditCard CC) {
        Account account = accountService.showAcc(idAcc).get();
        CreditCard creditCard = CC;
        creditCard.setAccountCC(account);
        creditCardService.createCC(creditCard);
        return "redirect:/users";
    }

    @PostMapping("/{idAcc}/cards/debit_card")
    public String createDebit(@PathVariable("idAcc") Long idAcc,
                              @ModelAttribute("DC") DebitCard DC){
        Account account = accountService.showAcc(idAcc).get();
        DebitCard debitCard = DC;
        debitCard.setAccountDC(account);
        debitCardService.createDC(debitCard);
        return "redirect:/users";
    }

    @PostMapping("/{idAcc}/cards/deposit")
    public String createDeposit(@PathVariable("idAcc") Long idAcc,
                                @ModelAttribute("deposit") Deposit deposit){
        Account account = accountService.showAcc(idAcc).get();
        Deposit depositCurr = deposit;
        depositCurr.setAccountDP(account);
        depositService.createDeposit(depositCurr);
        return "redirect:/users";
    }

}

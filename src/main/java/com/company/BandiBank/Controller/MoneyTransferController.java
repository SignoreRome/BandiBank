package com.company.BandiBank.Controller;

import com.company.BandiBank.Entity.Account;
import com.company.BandiBank.Entity.TransferMoney;
import com.company.BandiBank.Exceptions.NotEnoughMoneyException;
import com.company.BandiBank.Service.ServiceImpl.AccountServiceImpl;
import com.company.BandiBank.Service.ServiceImpl.TransferMoneyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/accounts")
public class MoneyTransferController {

    private AccountServiceImpl accountService;
    private TransferMoneyServiceImpl transferMoneyService;

    @Autowired
    public MoneyTransferController(AccountServiceImpl accountService, TransferMoneyServiceImpl transferMoneyService) {
        this.accountService = accountService;
        this.transferMoneyService = transferMoneyService;
    }

    @GetMapping("/transfers")
    public String index(Model model){
        model.addAttribute("transfers", transferMoneyService.findAll());
        return "/transfersPage";
    }

    @GetMapping("/{idAcc}/transfer_money")
    public String menu(@PathVariable("idAcc") Long idAcc, Model model){
        model.addAttribute("account", accountService.showAcc(idAcc).get());
        model.addAttribute("transaction", new TransferMoney());
        return "transferMoney";
    }

    @PostMapping("/{idAcc}/transfer_money/outer_transaction")
    public String outerTransaction(@PathVariable("idAcc") Long idAcc,
                                   @ModelAttribute("transaction") TransferMoney transaction){
        transferMoneyService.outerTransaction(idAcc, transaction);
        return "redirect:/users/accounts/{idAcc}";
    }

    @PostMapping("/{idAcc}/transfer_money/inner_transaction")
    public String innerTransaction(@PathVariable("idAcc") Long idAcc,
                                   @ModelAttribute("transaction") TransferMoney transaction){
        transferMoneyService.innerTransaction(idAcc, transaction);
        return "redirect:/users/accounts/{idAcc}";
    }

}

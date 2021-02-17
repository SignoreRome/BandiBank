package com.company.BandiBank.Controller;

import com.company.BandiBank.Entity.Account;
import com.company.BandiBank.Entity.User;
import com.company.BandiBank.Service.ServiceImpl.AccountServiceImpl;
import com.company.BandiBank.Service.ServiceImpl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class AccountsController {

    private AccountServiceImpl accountService;
    private UserServiceImp userServiceImp;

    @Autowired
    public AccountsController(AccountServiceImpl accountService, UserServiceImp userServiceImp) {
        this.accountService = accountService;
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/accounts/{accId}")
    public String showAcc(Model model, @PathVariable("accId") Long accId) {
        model.addAttribute("account", accountService.showAcc(accId).get());
        model.addAttribute("balance", accountService.sumBalance(accId));
        return "accountShow";
    }

    @GetMapping("/{userId}/accounts/new")
    public String newAcc(Model model,@PathVariable("userId") Long userId) {
        model.addAttribute("user", userServiceImp.getUserById(userId).get());
        model.addAttribute("account", new Account());
        return "accountNew";
    }

    @PostMapping("/{userId}/accounts")
    public String createAcc(@PathVariable("userId") Long userId,
                            @ModelAttribute("account") Account account){

        User user = userServiceImp.getUserById(userId).get();
        accountService.createAcc(account);
        user.setAccount(account);
        userServiceImp.save(user);
        return "redirect:/users";
    }
}

package com.itender.account.controller;

import com.itender.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:19
 * @desc :
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping
    public void debit(@RequestParam("userId") String userId, @RequestParam("money")int money) {
        accountService.debit(userId, money);
    }
}

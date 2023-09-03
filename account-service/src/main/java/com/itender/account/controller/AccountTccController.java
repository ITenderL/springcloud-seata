package com.itender.account.controller;

import com.itender.account.service.AccountTccService;
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
@RequestMapping("/tcc/account")
public class AccountTccController {

    private final AccountTccService accountTccService;

    @Autowired
    public AccountTccController(AccountTccService accountTccService) {
        this.accountTccService = accountTccService;
    }

    @PutMapping
    public void deduct(@RequestParam("userId") String userId, @RequestParam("money") int money) {
        accountTccService.deduct(userId, money);
    }
}

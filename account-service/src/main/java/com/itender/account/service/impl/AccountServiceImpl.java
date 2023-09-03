package com.itender.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itender.account.entity.Account;
import com.itender.account.mapper.AccountMapper;
import com.itender.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:53
 * @desc :
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public void debit(String userId, int money) {
        Account account = accountMapper.selectOne(new QueryWrapper<Account>().eq("user_id", userId));
        if (Objects.nonNull(account) && account.getMoney() < money) {
            throw new RuntimeException("余额不足！");
        }
        log.info("开始扣款...");
        accountMapper.debit(userId, money);
        log.info("扣款{}成功...", money);
    }
}

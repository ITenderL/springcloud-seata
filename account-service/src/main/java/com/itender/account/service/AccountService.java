package com.itender.account.service;


/**
 * @author : itender
 * @date : 2023/08/20/ 14:19
 * @desc :
 */
public interface AccountService {

    /**
     * 从用户账户中借出
     */
    void debit(String userId, int money);
}

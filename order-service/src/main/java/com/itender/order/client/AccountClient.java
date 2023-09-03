package com.itender.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : itender
 * @date : 2023/08/20/ 17:28
 * @desc :
 */
@FeignClient(name = "account-service")
public interface AccountClient {

    /**
     * 从用户账户中借出
     *
     * @param userId
     * @param money
     */
    @PutMapping("/account")
    void debit(@RequestParam("userId") String userId, @RequestParam("money")int money);

    /**
     * 从用户账户中借出
     *
     * @param userId
     * @param money
     */
    @PutMapping("/tcc/account")
    void deduct(@RequestParam("userId") String userId, @RequestParam("money") int money);
}

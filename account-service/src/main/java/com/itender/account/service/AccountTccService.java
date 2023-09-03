package com.itender.account.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author : itender
 * @date : 2023/08/23/ 21:50
 * @desc :
 */
@LocalTCC
public interface AccountTccService {

    /**
     * 从用户账户中借出
     *
     * @param userId
     * @param money
     */
    @TwoPhaseBusinessAction(name = "debit", commitMethod = "confirm", rollbackMethod = "cancel")
    void deduct(@BusinessActionContextParameter(paramName = "userId") String userId,
               @BusinessActionContextParameter(paramName = "money") int money);

    /**
     * 确认
     *
     * @param ctx
     * @return
     */
    boolean confirm(BusinessActionContext ctx);

    /**
     * 回滚
     *
     * @param ctx
     * @return
     */
    boolean cancel(BusinessActionContext ctx);
}

package com.itender.account.service.impl;

import com.itender.account.entity.AccountFreeze;
import com.itender.account.mapper.AccountFreezeMapper;
import com.itender.account.mapper.AccountMapper;
import com.itender.account.service.AccountTccService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.itender.account.entity.AccountFreeze.State.CANCEL;
import static com.itender.account.entity.AccountFreeze.State.TRY;

/**
 * @author : itender
 * @date : 2023/08/23/ 22:05
 * @desc :
 */
@Slf4j
@Service
public class AccountTccServiceImpl implements AccountTccService {

    private final AccountMapper accountMapper;

    private final AccountFreezeMapper accountFreezeMapper;

    @Autowired
    public AccountTccServiceImpl(AccountMapper accountMapper, AccountFreezeMapper accountFreezeMapper) {
        this.accountMapper = accountMapper;
        this.accountFreezeMapper = accountFreezeMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String userId, int money) {
        // 1.获取全局事务id
        String xid = RootContext.getXID();
        // 2.业务悬挂，如果freeze中有冻结记录，说明一定是执行过cancel了，因此要拒绝执行try的业务
        AccountFreeze oldFreeze = accountFreezeMapper.selectById(xid);
        if (Objects.nonNull(oldFreeze)) {
            return;
        }
        // 3.扣减可用余额
        accountMapper.debit(userId, money);
        // 4.记录冻结金额，事务状态
        AccountFreeze freeze = AccountFreeze.builder().xid(xid).userId(userId).freezeMoney(money).state(TRY.getValue()).build();
        accountFreezeMapper.insert(freeze);
    }

    @Override
    public boolean confirm(BusinessActionContext ctx) {
        // 1.获取全局事务id
        String xid = ctx.getXid();
        // 2.删除冻结金额
        int count = accountFreezeMapper.deleteById(xid);
        return count == 1;
    }

    @Override
    public boolean cancel(BusinessActionContext ctx) {
        // 1.查询冻结金额记录
        AccountFreeze freeze = accountFreezeMapper.selectById(ctx.getXid());
        // 2.如果记录为空，说明没有try，要空回滚
        if (Objects.isNull(freeze)) {
            String xid = ctx.getXid();
            String userId = ctx.getActionContext("userId").toString();
            freeze = AccountFreeze.builder().xid(xid).userId(userId).freezeMoney(0).state(CANCEL.getValue()).build();
            accountFreezeMapper.insert(freeze);
            return true;
        }
        // 3.幂等处理，如果已经执行过回滚了，就不需要再次执行了
        if (Objects.equals(CANCEL.getValue(), freeze.getState())) {
            return true;
        }
        // 4.恢复可用金额
        accountMapper.refound(freeze.getUserId(), freeze.getFreezeMoney());
        // 5.修改事务状态为cancel
        freeze.setFreezeMoney(0);
        freeze.setState(CANCEL.getValue());
        int count = accountFreezeMapper.updateById(freeze);
        return count == 1;
    }
}

package com.itender.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itender.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:20
 * @desc :
 */
@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 减钱
     *
     * @param userId
     * @param money
     */
    void debit(@Param("userId") String userId, @Param("money") int money);

    /**
     * 恢复金额
     *
     * @param userId
     * @param money
     */
    void refound(@Param("userId") String userId, @Param("money") int money);
}

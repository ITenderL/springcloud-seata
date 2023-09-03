package com.itender.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itender.account.entity.Account;
import com.itender.account.entity.AccountFreeze;
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
public interface AccountFreezeMapper extends BaseMapper<AccountFreeze> {

}

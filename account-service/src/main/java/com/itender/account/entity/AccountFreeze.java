package com.itender.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:15
 * @desc :
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_account_freeze")
public class AccountFreeze {

    @TableId(type = IdType.INPUT)
    private String xid;

    @TableField("user_id")
    private String userId;

    @TableField("freeze_money")
    private Integer freezeMoney;

    /**
     * 状态 0：try 1：confirm 2：cancel
     */
    private Integer state;

    @Getter
    public enum State{
        /**
         * 事务状态
         */
        TRY(0),
        CONFIRM(1),
        CANCEL(2);

        private Integer value;
        State(int value) {
            this.value = value;
        }
    }
}

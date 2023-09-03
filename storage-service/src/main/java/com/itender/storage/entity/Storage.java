package com.itender.storage.entity;

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
@TableName("t_storage")
public class Storage {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("commodity_code")
    private String commodityCode;

    private Integer count;
}

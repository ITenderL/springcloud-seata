package com.itender.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itender.storage.entity.Storage;
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
public interface StorageMapper extends BaseMapper<Storage> {

    /**
     * 减库存
     *
     * @param commodityCode
     * @param orderCount
     */
    void deduct(@Param("commodityCode") String commodityCode, @Param("orderCount") int orderCount);
}

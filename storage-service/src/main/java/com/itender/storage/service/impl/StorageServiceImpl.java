package com.itender.storage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.itender.storage.entity.Storage;
import com.itender.storage.mapper.StorageMapper;
import com.itender.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:51
 * @desc :
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    private final StorageMapper storageMapper;

    @Autowired
    public StorageServiceImpl(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }

    @Override
    public void deduct(String commodityCode, int count) {
        Storage storage = storageMapper.selectOne(new QueryWrapper<Storage>().eq("commodity_code", commodityCode));
        if (Objects.nonNull(storage) && storage.getCount() < count) {
            throw new RuntimeException("库存不足！");
        }
        storageMapper.deduct(commodityCode, count);
    }
}

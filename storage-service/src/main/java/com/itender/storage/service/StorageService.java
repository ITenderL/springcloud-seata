package com.itender.storage.service;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:19
 * @desc :
 */

public interface StorageService {

    /**
     * 扣除存储数量
     */
    void deduct(String commodityCode, int count);
}

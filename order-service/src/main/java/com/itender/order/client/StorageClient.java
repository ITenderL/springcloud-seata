package com.itender.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : itender
 * @date : 2023/08/20/ 17:40
 * @desc :
 */
@FeignClient("storage-service")
public interface StorageClient {

    /**
     * 减库存
     *
     * @param commodityCode
     * @param count
     */
    @PutMapping("/storage")
    void deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") int count);
}

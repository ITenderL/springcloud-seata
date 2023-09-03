package com.itender.storage.controller;

import com.itender.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:19
 * @desc :
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PutMapping
    public void deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") int count) {
        storageService.deduct(commodityCode, count);
    }
}

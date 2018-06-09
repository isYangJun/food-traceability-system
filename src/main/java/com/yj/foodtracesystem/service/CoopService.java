package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.ProductInfo;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 12:34 2018/5/18
 */
public interface CoopService {
    List<ComInfo> findAll();

    List<ComInfo> findComInfoByComNum(String comNum);

    List<ComInfo> findComInfoByComAdd(String comAdd);

    List<ComInfo> findComInfoByNumorAdd(String comNum, String comAdd);

    List<ComInfo> findDistinctComAdd();

    void updateComInfo(String comName, String comAdd, String comNum);

    void saveProInfo(ProductInfo productInfo);

    List<ProductInfo> findBySeedName(String seedName);

    List<ProductInfo> findByFiledNum(int id);

    List<ProductInfo> findByFiledNumOrSeedName(int id, String seedName);

    List<ProductInfo> findByHarvTime(String startTime, String endTime);

    ProductInfo findByProductNum(String productNum);
    List<ProductInfo> findByProductNumIn(String productNum);
    int findSeedIdByProductNum(String productNum);
}

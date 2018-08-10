package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.*;

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

    String findSeedNameBySeedNum(int seedId);

    void saveproBachInfo(ProductBatchInfo productBatchInfo);

    List<ProductBatchInfo> findProBachInfoByBachNum(String proBachNum);

    List<ProductBatchInfo> findBySeedNum(int seedId);

    List<ProductBatchInfo> findProBatchInfoByTime(String startTime, String endTime);

    List<ProductBatchInfo> findAllProBatchInfo();

    String findProBatchNumByProNum(String proNum);

    int findProBatchWeightByBatchNum(String batchNum);

    int findCompanyRoleByComNum(String comNum);

    int findProWeightByProNum(String proNum);

    List<User> findByUserCompAndRole(String userComp, int userRole);

    OperationOrderInfo saveOperationOrderInfo(OperationOrderInfo operationOrderInfo);

    List<OperationOrderInfo> findAllOperationOrderInfo();
    List<OperationOrderInfo> findByUserIdAndIsDone(String UserId,int isDone);
}

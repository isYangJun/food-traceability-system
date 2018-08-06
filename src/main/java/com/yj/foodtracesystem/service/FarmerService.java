package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationAddPara;
import com.yj.foodtracesystem.model.TempModel.OperationHisPara;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 10:08 2018/4/21
 */
public interface FarmerService {

    List<FiledInfo> findAllFiledInfo();
    List<FiledOperationType> findAllFiledOperationType();
    List<OperationHisResult> findOperaHisByPara(OperationHisPara operationHisPara)throws Exception;
    List<SeedInfo> findAllSeedInfo();
    List<FertilizerInfo> findAllFertilizerInfo();
    List<PesticideInfo> findAllPesticideInfo();
    void saveFiledOperation(FiledOperation filedOperation);
    void saveFiledInfo(FiledInfo filedInfo);
    void saveSeedInfo(SeedInfo seedInfo);
    void saveFertilizerInfo(FertilizerInfo fertilizerInfo);
    void savePesticideInfo(PesticideInfo pesticideInfo);
    List<FiledInfo> findFiledInfoById(int id);
    List<FiledInfo> findByTime(String startTime,String endTime);
    List<SeedInfo> findSeedByTime(String startTime,String endTime);
    List<SeedInfo> findSeedInfoById(int id);
    List<FertilizerInfo> findFertilizerInfoById(int id);
    List<FertilizerInfo> findFertilizerInfoByTime(String startTime,String endTime);
    List<PesticideInfo> findPesticideInfoById(int id);
    List<PesticideInfo> findPesticideInfoByTime(String startTime,String endTime);
    SeedInfo findBySeedId(int id);
    List<FiledOperation> findFiledOperationBySeedId(int id);
    OperationOrderInfo updateOrderInfo(int id);

}

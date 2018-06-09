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
    void saveFiledOperation(FiledOperation filedOperation);
    void saveFiledInfo(FiledInfo filedInfo);
    void saveSeedInfo(SeedInfo seedInfo);
    List<FiledInfo> findFiledInfoById(int id);
    List<FiledInfo> findByTime(String startTime,String endTime);
    List<SeedInfo> findSeedByTime(String startTime,String endTime);
    List<SeedInfo> findSeedInfoById(int id);
    SeedInfo findBySeedId(int id);
    List<FiledOperation> findFiledOperationBySeedId(int id);

}

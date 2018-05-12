package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.*;

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
}

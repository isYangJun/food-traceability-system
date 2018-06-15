package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.ProductInfo;
import com.yj.foodtracesystem.model.TransStationInfo;
import com.yj.foodtracesystem.model.TransportInfo;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 7:39 2018/5/27
 */
public interface TransporterService {
    List<ProductInfo> findTranstedProductInfo();
    List<TransStationInfo> findTransStationInfo();
    List<ComInfo> findTransOrReposInfo();
    List<TransportInfo> findTransInfoByProNum(String proNum);
    List<TransportInfo> findTransInfoByTime(String startTime,String endTime);
    void saveTransInfo(TransportInfo transportInfo);
    void saveTransStationInfo(TransStationInfo transStationInfo);
    List<TransStationInfo> findTransStationInfoByCarNum(String carNum);
    List<TransStationInfo> findTransStationInfoByTime(String startTime,String endTime);
    List<TransStationInfo> findAllInfo();
}

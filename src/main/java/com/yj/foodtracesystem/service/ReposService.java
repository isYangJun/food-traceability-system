package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.RepositoryInfo;
import com.yj.foodtracesystem.model.RepositoryStationInfo;
import com.yj.foodtracesystem.model.TransportInfo;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:24 2018/5/30
 */
public interface ReposService {
    List<RepositoryInfo> findRepositoryInfoByProNum(String proNum);
    List<RepositoryInfo> findRepositoryInfoByTime(String startTime,String endTime);
    void saveRepositoryInfo(RepositoryInfo repositoryInfo);
    List<TransportInfo> findOwnedTransportInfo(String comNum);
    List<RepositoryStationInfo> findAllRepositoryStationInfo();
    String findWareNameByWarehouseNum(String warehouseNum);
    String findSaveTempByWarehouseNum(String warehouseNum);
    String findByProNum(String proNum);
    List<RepositoryStationInfo> findReposStationInfoByReposNum(String comNum);
}

package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.TransStationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:52 2018/5/27
 */
@Repository("transStationRepository")
public interface TransStationRepository extends JpaRepository<TransStationInfo,Integer> {
    @Override
    List<TransStationInfo> findAll();
    List<TransStationInfo> findTransStationInfoByCarNum(String carNum);
    List<TransStationInfo> findTransStationInfoByCarRegTimeBetween(String startTime,String endTime);

}

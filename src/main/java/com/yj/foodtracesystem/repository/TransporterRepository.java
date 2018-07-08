package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.TransportInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 7:50 2018/5/27
 */
@Repository("transporterRepository")
public interface TransporterRepository extends JpaRepository<TransportInfo,Integer> {
    List<TransportInfo> findByProNum(String proNum);
    List<TransportInfo> findByRecordedTimeBetween(String startTime,String endTime);
    TransportInfo findByDestinationNumAndProNum(String destinationNum,String proNum);

}

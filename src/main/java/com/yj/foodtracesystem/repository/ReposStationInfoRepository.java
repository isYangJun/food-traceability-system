package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.RepositoryStationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:19 2018/5/30
 */
@Repository("reposStationInfoRepository")
public interface ReposStationInfoRepository extends JpaRepository<RepositoryStationInfo,Integer> {
    @Override
    List<RepositoryStationInfo> findAll();

    RepositoryStationInfo findByWarehouseNum(String warehouseNum);
    List<RepositoryStationInfo> findByRepositoryNumIn(String comNum);
}

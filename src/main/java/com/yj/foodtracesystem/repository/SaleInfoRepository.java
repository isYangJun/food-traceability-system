package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.SaleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 20:01 2018/6/3
 */
@Repository("saleInfoRepository")
public interface SaleInfoRepository extends JpaRepository<SaleInfo, Integer> {
    @Override
    List<SaleInfo> findAll();
    List<SaleInfo> findByProNum(String proNum);
    List<SaleInfo> findByRecordedTimeBetween(String startTime, String endTime);
}

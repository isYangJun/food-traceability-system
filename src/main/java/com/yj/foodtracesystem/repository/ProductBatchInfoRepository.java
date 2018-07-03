package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.ProductBatchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date:
 */
@Repository("productBachInfoRepository")
public interface ProductBatchInfoRepository extends JpaRepository<ProductBatchInfo,Integer> {
    @Override
    List<ProductBatchInfo> findAll();

    List<ProductBatchInfo> findByProBatchNum(String proBachNum);
    List<ProductBatchInfo> findBySeedNum(int seedNum);
    List<ProductBatchInfo> findByHarvTimeBetween(String startTime, String endTime);
}

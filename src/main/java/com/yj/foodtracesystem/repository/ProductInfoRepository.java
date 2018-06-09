package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 21:12 2018/5/18
 */
@Repository("productInfoRepository")
public interface ProductInfoRepository extends JpaRepository<ProductInfo,Integer>{
    @Override
    List<ProductInfo> findAll();
    List<ProductInfo> findBySeedName(String seedName);
    List<ProductInfo> findByFiledNum(int id);
    List<ProductInfo> findByHarvTimeBetween(String startTime,String endTime);
    ProductInfo findByProNum(String proNum);
    List<ProductInfo> findByProNumIn(String proNum);
}

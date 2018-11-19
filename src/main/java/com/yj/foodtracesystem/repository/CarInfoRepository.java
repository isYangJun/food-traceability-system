package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 14:57 2018/11/19
 */
@Repository("carInfoRepository")
public interface CarInfoRepository extends JpaRepository<Car,Integer> {
    @Override
    List<Car> findAll();
}

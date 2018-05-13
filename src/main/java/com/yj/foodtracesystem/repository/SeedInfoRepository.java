package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.SeedInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 14:00 2018/5/13
 */
@Repository("seedInfoRepository")
public interface SeedInfoRepository extends JpaRepository<SeedInfo,Integer> {
    @Override
    List<SeedInfo> findAll();
   SeedInfo findById(int id);
}

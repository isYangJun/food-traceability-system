package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.FertilizerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;



@Repository("fertilizerInfoRepository")
public interface FertilizerInfoRepository extends JpaRepository<FertilizerInfo,Integer> {
    @Override
    List<FertilizerInfo> findAll();

    FertilizerInfo findById(int id);

    List<FertilizerInfo> findByFertilizerRegTimeBetween(String startTime, String endTime);
}


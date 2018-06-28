package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.PesticideInfo;
import com.yj.foodtracesystem.model.PesticideInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pesticideInfoRepository")
public interface PesticideInfoRepository extends JpaRepository<PesticideInfo,Integer> {
    @Override
    List<PesticideInfo> findAll();

    PesticideInfo findById(int id);

    List<PesticideInfo> findByPesticideRegTimeBetween(String startTime, String endTime);
}

package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.FiledInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 16:09 2018/5/7
 */
@Repository("filedInfoRepository")
public interface FiledInfoRepository extends JpaRepository<FiledInfo,Integer> {
    @Override
    List<FiledInfo> findAll();
    FiledInfo findById(int id);
    List<FiledInfo> findByFiledRegTimeBetween(String startTime, String endTime);
}

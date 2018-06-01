package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.RepositoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:18 2018/5/30
 */
@Repository("reposInfoRepository")
public interface ReposInfoRepository extends JpaRepository<RepositoryInfo,Integer> {
    List<RepositoryInfo> findByProNum(String proNum);
    List<RepositoryInfo> findByRecordedTimeBetween(String startTime,String endTime);
}

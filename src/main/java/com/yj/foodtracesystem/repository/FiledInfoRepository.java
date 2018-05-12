package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.FiledInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 16:09 2018/5/7
 */
@Repository("filedInfoRepository")
public interface FiledInfoRepository extends JpaRepository<FiledInfo,Integer> {
    @Override
    List<FiledInfo> findAll();
}

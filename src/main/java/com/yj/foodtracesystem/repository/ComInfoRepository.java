package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.FiledInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 12:35 2018/5/18
 */
@Repository("comInfoRepository")
public interface ComInfoRepository extends JpaRepository<ComInfo,Integer> {
    @Override
    List<ComInfo> findAll();
    List<ComInfo> findByComNum(String comNum);
    List<ComInfo>findByComAdd(String comAdd);
}

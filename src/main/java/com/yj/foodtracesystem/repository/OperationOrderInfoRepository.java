package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.OperationOrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 15:31 2018/7/15
 */
@Repository("operationOrderInfoRepository")
public interface OperationOrderInfoRepository extends JpaRepository<OperationOrderInfo,Integer> {
List<OperationOrderInfo> findByUserIdAndIsDone(String UserId,int isDone);
OperationOrderInfo findById(int id);
}

package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.FiledOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 14:17 2018/5/7
 */
@Repository("filedOperationRepository")
public interface FiledOperationRepository extends JpaRepository<FiledOperation, Integer> {
    @Override
    List<FiledOperation> findAll();
    List<FiledOperation> findBySeedId(int id);
}

package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.FiledOperation;
import com.yj.foodtracesystem.model.FiledOperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 16:05 2018/5/7
 */
@Repository("filedOperationTypeRepository")
public interface FiledOperationTypeRepository extends JpaRepository<FiledOperationType,Integer> {
    @Override
    List<FiledOperationType> findAll();
    FiledOperationType findById(int id);
}

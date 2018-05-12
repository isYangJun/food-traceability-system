package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.FiledInfo;
import com.yj.foodtracesystem.model.FiledOperationType;
import com.yj.foodtracesystem.model.OperationHisPara;
import com.yj.foodtracesystem.model.OperationHisResult;
import com.yj.foodtracesystem.repository.FiledInfoRepository;
import com.yj.foodtracesystem.repository.FiledOperationRepository;
import com.yj.foodtracesystem.repository.FiledOperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 7:03 2018/5/7
 */
@Service("farmerService")
public class FarmerServiceImp implements FarmerService {
    @Autowired
    private FiledOperationRepository filedOperationRepository;
    @Autowired
    private FiledInfoRepository filedInfoRepository;
    @Autowired
    private FiledOperationTypeRepository filedOperationTypeRepository;
    @Autowired
    private PublicService publicService;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FiledInfo> findAllFiledInfo() {

        return filedInfoRepository.findAll();
    }

    @Override
    public List<FiledOperationType> findAllFiledOperationType() {
        return filedOperationTypeRepository.findAll();

    }

    @Override
    public List<OperationHisResult> findOperaHisByPara(OperationHisPara operationHisPara) throws Exception {
        String startTime = operationHisPara.startTime.replaceAll("T", " ");
        String endTime = operationHisPara.endTime.replaceAll("T", " ");
        int filedId = operationHisPara.filedId;

        String sql = "SELECT fo_filed_id AS filedId,fo_filed_name AS filedName,fo_user_id AS userNum,tbl_user.name AS userName,\n" +
                "fo_operation_name AS operaInfo,fo_seed_name AS seedName,fo_operation_time AS operationTime,fo_operation_memo AS memo FROM tbl_filed_operation" +
                "  LEFT  JOIN tbl_user\n" +
                "ON tbl_filed_operation.fo_user_id=tbl_user.user_num WHERE tbl_filed_operation.fo_filed_id= " + filedId +
                " AND fo_operation_time between '" + startTime +
                "' and '" + endTime + "'";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> list = nativeQuery.getResultList();
        List<OperationHisResult> operationHisResultList = publicService.convertToType(list);
       /* String sql1 = "select * from tbl_filed_operation where fo_operation_time between '" + startTime +
                "' and '" + endTime + "'";
        List<Object> list1 = entityManager.createNativeQuery(sql1).getResultList();*/
        return operationHisResultList;
    }
}

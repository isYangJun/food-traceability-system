package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationAddPara;
import com.yj.foodtracesystem.model.TempModel.OperationHisPara;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import com.yj.foodtracesystem.repository.FiledInfoRepository;
import com.yj.foodtracesystem.repository.FiledOperationRepository;
import com.yj.foodtracesystem.repository.FiledOperationTypeRepository;
import com.yj.foodtracesystem.repository.SeedInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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
    private SeedInfoRepository seedInfoRepository;
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
        List<OperationHisResult> operationHisResultList = publicService.convertToType(list,OperationHisResult.class.getName());
       /* String sql1 = "select * from tbl_filed_operation where fo_operation_time between '" + startTime +
                "' and '" + endTime + "'";
        List<Object> list1 = entityManager.createNativeQuery(sql1).getResultList();*/
        return operationHisResultList;
    }

    @Override
    public void saveFiledOperation(FiledOperation filedOperation) {
       String seedName=seedInfoRepository.findById(filedOperation.getSeedId()).getSeedName();
       String filedName=filedInfoRepository.findById(filedOperation.getFiledId()).getFiledName();
       String operationName=filedOperationTypeRepository.findById(filedOperation.getOperateTypeId()).getOperationName();
       filedOperation.setSeedName(seedName);
       filedOperation.setFiledName(filedName);
       filedOperation.setOperationName(operationName);
       filedOperationRepository.save(filedOperation);
    }

    @Override
    public List<SeedInfo> findAllSeedInfo() {
        return seedInfoRepository.findAll();
    }

    @Override
    public void saveFiledInfo(FiledInfo filedInfo) {
        filedInfoRepository.save(filedInfo);
    }

    @Override
    public List<FiledInfo> findFiledInfoById(int id) {
        List<FiledInfo> filedInfoList=new ArrayList<>();
        filedInfoList.add(filedInfoRepository.findById(id)) ;
        return filedInfoList;
    }

    @Override
    public List<FiledInfo> findByTime(String startTime, String endTime) {
        return filedInfoRepository.findByFiledRegTimeBetween(startTime,endTime);
    }

    @Override
    public SeedInfo findBySeedId(int id) {
        return seedInfoRepository.findById(id);
    }
}

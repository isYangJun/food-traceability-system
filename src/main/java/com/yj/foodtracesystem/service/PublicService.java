package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.OperationHisResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 18:59 2018/5/12
 */
@Service("publicService")
public class PublicService {

    public List<OperationHisResult> convertToType( List<Object[]> list) {
        List<OperationHisResult> operationHisResultList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            OperationHisResult operationHisResult=new OperationHisResult();
            operationHisResult.filedId=list.get(i)[0].toString();
            operationHisResult.filedName=list.get(i)[1].toString();
            operationHisResult.userNum=list.get(i)[2].toString();
            operationHisResult.userName=list.get(i)[3].toString();
            operationHisResult.operaInfo=list.get(i)[4].toString();
            operationHisResult.seedName=list.get(i)[5].toString();
            operationHisResult.operationTime=list.get(i)[6].toString().replace(".0","");
            operationHisResult.memo=list.get(i)[7].toString();
            operationHisResultList.add(operationHisResult);
        }
        return operationHisResultList;
    }
}
